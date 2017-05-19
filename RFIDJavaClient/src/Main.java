
public class Main {
	
	public static void main(String[] args){
		RfidReader<EpcTag> reader = new RfidReader<>(new EpcParser(), System.out::println);
		
	}
}
