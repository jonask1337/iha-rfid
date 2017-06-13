import java.util.BitSet;

public class EpcParser implements RfidParser<EpcTag>{

	@Override
	public  EpcTag parse(String tagData) {
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

		//String.format("%016X", part1);
		/*
		try {
			byte[] bytes = new byte[4* tagData.size()];
			for (int i = 0; i < tagData.size(); i++){
				int word = tagData.get(i);
				for(int j = 0; j < 4; j++ ){
					bytes[i*4+j] = (byte) ((word & (0xFF << (3-j)*4)) >> (3-j)*4);
				}
			}
			BitSet bitSet = BitSet.valueOf(bytes);
			System.out.println(bitSet.size());
			if (tagData.size() >= 3) {
				header = (short) (bitSet.get(0,8).toLongArray()[0]);
				domainManager = (int) (bitSet.get(8,36).toLongArray()[0]);
				objectClassNumber = (int) (bitSet.get(36, 60).toLongArray()[0]);
				serialNumber = (bitSet.get(60, 96).toLongArray()[0]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}*/
		return new EpcTag(header, domainManager, objectClassNumber, serialNumber);
	}
}
