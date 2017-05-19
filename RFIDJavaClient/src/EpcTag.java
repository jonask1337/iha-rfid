
public class EpcTag implements RfidTag {
	short header;
	int domainManager;
	int objectClassNumber;
	long serialNumber;
	
	public EpcTag(short header, int domainManager, int objectClassNumber, long serialNumber) {
		super();
		this.header = header;
		this.domainManager = domainManager;
		this.objectClassNumber = objectClassNumber;
		this.serialNumber = serialNumber;
	}
	
	public short getHeader() {
		return header;
	}
	public void setHeader(short header) {
		this.header = header;
	}
	@Override
	public String toString() {
		return "EpcTag [header=" + header + ", domainManager=" + domainManager + ", objectClassNumber="
				+ objectClassNumber + ", serialNumber=" + serialNumber + "]";
	}

	public int getDomainManager() {
		return domainManager;
	}
	public void setDomainManager(int domainManager) {
		this.domainManager = domainManager;
	}
	public int getObjectClassNumber() {
		return objectClassNumber;
	}
	public void setObjectClassNumber(int objectClassNumber) {
		this.objectClassNumber = objectClassNumber;
	}
	public long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}
	


}
