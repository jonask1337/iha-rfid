public interface RfidTag {

    void setHeader(short header);

    void setDomainManager(int domainManager);

    void setObjectClass(int objectClass);

    void setSerialNumber(long serialNumber);

    void setAntenna(int antenna);
}
