import com.impinj.octane.Tag;

public class EpcParser implements RfidParser<EpcTag>{

	@Override
	public  EpcTag parse(Tag tag) {
		String tagData =  tag.getEpc().toHexString();

		short header = 0;
		int domainManager = 0;
		int objectClassNumber = 0;
		long serialNumber = 0;

		String hesderString = tagData.substring(0, 2);
		String domainManagerString = tagData.substring(2, 9);
		String objectClassNumberString = tagData.substring(9, 15);
		String serialNumberString = tagData.substring(15, 24);

		header = Short.parseShort(hesderString);
		domainManager = Integer.parseInt(domainManagerString);
		objectClassNumber = Integer.parseInt(objectClassNumberString);
		serialNumber = Long.parseLong(serialNumberString);

		return new EpcTag(header, domainManager, objectClassNumber, serialNumber, tag.getAntennaPortNumber());
	}
}
