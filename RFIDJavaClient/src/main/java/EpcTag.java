
public class EpcTag implements RfidTag {
	private short header;
	private int domainManager;
	private int objectClass;
	private long serialNumber;
	private int antenna;

	public EpcTag(short header, int domainManager, int objectClass, long serialNumber, int antenna) {
		super();
		this.header = header;
		this.domainManager = domainManager;
		this.objectClass = objectClass;
		this.serialNumber = serialNumber;
		this.antenna = antenna;
	}
	
	public short getHeader() {
		return header;
	}
	@Override
	public void setHeader(short header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "EpcTag [header=" + header + ", domainManager=" + domainManager + ", objectClass="
				+ objectClass + ", serialNumber=" + serialNumber + "]";
	}

	public int getDomainManager() {
		return domainManager;
	}
	@Override
	public void setDomainManager(int domainManager) {
		this.domainManager = domainManager;
	}

	public int getObjectClass() {
		return objectClass;
	}
	@Override
	public void setObjectClass(int objectClass) {
		this.objectClass = objectClass;
	}

	public long getSerialNumber() {
		return serialNumber;
	}
	@Override
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getAntenna() {
		return antenna;
	}
	@Override
	public void setAntenna(int antenna) {
		this.antenna = antenna;
	}
}
