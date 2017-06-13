/**
 * Created by Jonas on 09.06.2017.
 */
public class Product {
    private int location;
    private short header;
    private int domainManager;
    private int objectClass;
    private long serialNumber;

    public Product( short header, int domainManager, int objectClass, long serialNumber, int location) {
        this.location = location;
        this.header = header;
        this.domainManager = domainManager;
        this.objectClass = objectClass;
        this.serialNumber = serialNumber;
    }

    public static Product fromEpc(EpcTag tag, int location){
        return new Product(tag.getHeader(), tag.getDomainManager(), tag.getObjectClass(), tag.getSerialNumber(), location);
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public short getHeader() {
        return header;
    }

    public void setHeader(short header) {
        this.header = header;
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
