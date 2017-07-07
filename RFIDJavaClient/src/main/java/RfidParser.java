import com.impinj.octane.Tag;

public interface RfidParser <T extends RfidTag>{
	T parse(Tag tagData);
	
}
