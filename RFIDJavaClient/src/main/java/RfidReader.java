import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.impinj.octane.*;

public class RfidReader<T extends RfidTag> {
    private Consumer<List<T>> onRead;
    private RfidParser<T> parser;
    private ImpinjReader reader;
    private int opSpecID = 1;
    private boolean hasData = false;
    private int antennaCount;

    public RfidReader(RfidParser<T> parser, Consumer<List<T>> onRead, int antennaCount) {
        super();
        this.onRead = onRead;
        this.parser = parser;
        this.antennaCount = antennaCount;

        initializeReader();
    }

    private void initializeReader() {
        try {
            String hostname = "speedwayr-10-2B-15.local";
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

            for(short i = 1; i < antennaCount;i++){
                antennas.getAntenna(i).setIsMaxRxSensitivity(false);
                antennas.getAntenna(i).setIsMaxTxPower(false);
                antennas.getAntenna(i).setTxPowerinDbm(20.0);
                antennas.getAntenna(i).setRxSensitivityinDbm(-70);
                antennas.enableById(new short[]{i});
            }

            reader.setReaderStopListener((a, b) -> {
                if(!hasData){
                    onRead.accept(new ArrayList<T>() {
                    });
                }
                hasData = false;
            });

            reader.setTagReportListener((ImpinjReader impinjReader, TagReport tagReport) -> {
                //List<T> parsedTags = tagReport.getTags().stream().map(Tag::getEpc).map(TagData::toHexString).map(parser::parse).collect(Collectors.toList());
                List<T> parsedTags = tagReport.getTags().stream().map(parser::parse).collect(Collectors.toList());
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
