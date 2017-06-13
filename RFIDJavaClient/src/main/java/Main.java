import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args){
		final Server server = new Server("http://localhost", 8000);
		RfidReader<EpcTag> reader = new RfidReader<>(new EpcParser(), tags -> {
			List<Product> productList = tags.stream().map(epcTag -> Product.fromEpc(epcTag, 1)).collect(Collectors.toList());
			ProductList result = new ProductList(productList);
			server.updateProducts(result);
			System.out.println("Hallo??");
		});

		Scanner s = new Scanner(System.in);

		while (!s.nextLine().equals("exit")){
			System.out.println("Press Enter to close the door!");
			reader.start();
		}
		reader.stop();
	}
}
