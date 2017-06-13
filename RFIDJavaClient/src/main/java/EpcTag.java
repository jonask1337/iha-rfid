
public class EpcTag implements RfidTag {
	short header;
	int domainManager;
	int objectClass;
	long serialNumber;
	
	public EpcTag(short header, int domainManager, int objectClass, long serialNumber) {
		super();
		this.header = header;
		this.domainManager = domainManager;
		this.objectClass = objectClass;
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
		return "EpcTag [header=" + header + ", domainManager=" + domainManager + ", objectClass="
				+ objectClass + ", serialNumber=" + serialNumber + "]";
	}

	public int getDomainManager() {
		return domainManager;
	}
	public void setDomainManager(int domainManager) {
		this.domainManager = domainManager;
	}
	public int getObjectClass() {
		return objectClass;
	}
	public void setObjectClass(int objectClass) {
		this.objectClass = objectClass;
	}
	public long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}
	


}
