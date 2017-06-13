public interface RfidParser <T extends RfidTag>{
	T parse(String tagData);
	
}
