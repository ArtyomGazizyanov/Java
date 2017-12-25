package atom.volgatech.model.productContainer;

import atom.volgatech.model.product.Product;

import java.util.Map;

public interface ProductContainerInterface {
    void addItem(Product product);
    boolean isEmpty();
    Map<Product, Integer> getGroceryList();
}
