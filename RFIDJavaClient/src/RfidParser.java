import java.util.List;

public interface RfidParser <T extends RfidTag>{
	T parse(List<Integer> dataWords);
	
}
