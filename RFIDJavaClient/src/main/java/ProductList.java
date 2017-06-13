import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 09.06.2017.
 */
public class ProductList {
    private List<Product> products;

    public ProductList(List<Product> products){
        this.products = products;
    }
    public ProductList(){
        this.products = new ArrayList<Product>();
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
