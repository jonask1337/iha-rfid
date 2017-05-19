import java.util.List;

public class EpcParser implements RfidParser<EpcTag>{

	@Override
	public  EpcTag parse(List<Integer> dataWords) {
		// TODO Auto-generated method stub
		short header = 0;
		int domainManager = 0;
		int objectClassNumber = 0;
		long serialNumber = 0;
		
		if (dataWords.size() >= 3){
			header = (short) ((dataWords.get(0)<<8) & 0xff); 
		}
		EpcTag result = new EpcTag(header, domainManager, objectClassNumber, serialNumber);
	
		System.out.println(dataWords);
				
		return result;
		
	}
}
