import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.impinj.octane.*;

public class RfidReader<T extends RfidTag> {
    private Consumer<List<T>> onRead;
    private RfidParser<T> parser;
    private ImpinjReader reader;
    int opSpecID = 1;
    boolean hasData = false;

    public RfidReader(RfidParser<T> parser, Consumer<List<T>> onRead) {
        super();
        this.onRead = onRead;
        this.parser = parser;

        initializeReader();
    }

    private void initializeReader() {
        try {
            //String hostname = System.getProperty(SampleProperties.hostname);
            String hostname = "speedwayr-10-2B-15.local";
                /*if (hostname == null) {
	                throw new Exception("Must specify the '"
	                        + SampleProperties.hostname + "' property");
	            }
				*/
            reader = new ImpinjReader();

            System.out.println("Connecting");
            reader.connect(hostname);

            Settings settings = reader.queryDefaultSettings();

            ReportConfig report = settings.getReport();
            report.setIncludeAntennaPortNumber(true);
            report.setMode(ReportMode.BatchAfterStop);

            // The reader can be set into various modes in which reader
            // dynamics are optimized for specific regions and environments.
            // The following mode, AutoSetDenseReader, monitors RF noise and interference and then automatically
            // and continuously optimizes the reader’s configuration
            settings.setReaderMode(ReaderMode.AutoSetDenseReader);
            settings.getAutoStart().setMode(AutoStartMode.None);
            //settings.getAutoStart().setPeriodInMs(100);

            settings.getAutoStop().setMode(AutoStopMode.Duration);
            settings.getAutoStop().setDurationInMs(100);
            // set some special settings for antenna 1
            AntennaConfigGroup antennas = settings.getAntennas();
            antennas.disableAll();
            antennas.enableById(new short[]{1});
            antennas.getAntenna((short) 1).setIsMaxRxSensitivity(false);
            antennas.getAntenna((short) 1).setIsMaxTxPower(false);
            antennas.getAntenna((short) 1).setTxPowerinDbm(20.0);
            antennas.getAntenna((short) 1).setRxSensitivityinDbm(-70);

            reader.setReaderStopListener((a, b) -> {
                if(!hasData){
                    onRead.accept(new ArrayList<T>() {
                    });
                }
                hasData = false;
            });

            reader.setTagReportListener((ImpinjReader impinjReader, TagReport tagReport) -> {
                List<T> parsedTags = tagReport.getTags().stream().map(Tag::getEpc).map(TagData::toHexString).map(parser::parse).collect(Collectors.toList());
                onRead.accept(parsedTags);
                try {
                    reader.stop();
                } catch (OctaneSdkException e) {
                    e.printStackTrace();
                }
                hasData = true;
            });

            System.out.println("Applying Settings");
            reader.applySettings(settings);
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }

    public void start() {
        System.out.println("Starting");
        try {
            reader.start();
        } catch (OctaneSdkException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            reader.stop();
        } catch (OctaneSdkException e) {
            e.printStackTrace();
        }
        reader.disconnect();
        System.out.println("Reader stopped");

    }

    public Consumer<List<T>> getOnRead() {
        return onRead;
    }

    public void setOnRead(Consumer<List<T>> onRead) {
        this.onRead = onRead;
    }
}
