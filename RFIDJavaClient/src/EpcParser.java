import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.BitSet;
import java.util.List;

public class EpcParser implements RfidParser<EpcTag>{

	@Override
	public  EpcTag parse(List<Integer> dataWords) {
		short header = 0;
		int domainManager = 0;
		int objectClassNumber = 0;
		long serialNumber = 0;

		try {
			byte[] bytes = new byte[4*dataWords.size()];
			for (int i= 0; i < dataWords.size(); i++){
				int word = dataWords.get(i);
				for(int j = 0; j < 4; j++ ){
					bytes[i*4+j] = (byte) ((word & (0xFF << (3-j)*4)) >> (3-j)*4);
				}
			}
			BitSet bitSet = BitSet.valueOf(bytes);
			if (dataWords.size() >= 3) {
				header = (short) (bitSet.get(0,8).toLongArray()[0]);
				domainManager = (int) (bitSet.get(8,36).toLongArray()[0]);
				objectClassNumber = (int) (bitSet.get(36, 60).toLongArray()[0]);
				serialNumber = (bitSet.get(60, 96).toLongArray()[0]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new EpcTag(header, domainManager, objectClassNumber, serialNumber);
	}
}
