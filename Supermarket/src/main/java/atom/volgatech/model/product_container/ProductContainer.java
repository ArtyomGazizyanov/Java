package atom.volgatech.model.product_container;

import atom.volgatech.model.product.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductContainer implements ProductContainerInterface{
    public void addItem(Product product)
    {
        Integer productAmount = 1;
        if(_groceryList.containsKey(product)) {
            _groceryList.put(product, _groceryList.get(product) + productAmount);
        } else {
            _groceryList.put(product, productAmount);
        }
    }

    public Map<Product, Integer> getGroceryList()
    {
        return _groceryList;
    }

    public boolean isEmpty() {
        return _groceryList.isEmpty();
    }

    private Map<Product, Integer> _groceryList = new HashMap<Product, Integer>();
}
