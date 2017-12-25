package atom.volgatech.model.productContainer;

import atom.volgatech.model.product.Product;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductContainerTest {
    Product product = new Product(Product.ProductType.Food, "ProductKind", 10, 2);
    ProductContainer pContainer;

    ProductContainerTest() throws InvalidArgumentException {
    }

    @BeforeEach
    void setUp() {
        ProductContainer pContainer = new ProductContainer();
    }

    @Test
    void addItem() {
        ProductContainer pContainer = new ProductContainer();
        pContainer.addItem(product);
    }

    @Test
    void ammountChanged() {
        ProductContainer pContainer = new ProductContainer();
        pContainer.addItem(product);
        assertFalse(pContainer.isEmpty());
    }

    @Test
    void getCheckGroceryList() {
        ProductContainer pContainer = new ProductContainer();
        Product existedProduct = null;
        try {
            existedProduct = new Product(Product.ProductType.Drinks, "ProductKind", 10, 2);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        pContainer.addItem(existedProduct);
        assertFalse(pContainer.isEmpty());
    }

    @Test
    void getGroceryList() {
        ProductContainer pContainer = new ProductContainer();
        pContainer.addItem(product);
        int productCountFromGroceryList = pContainer.getGroceryList().get(product);
        assertEquals( 1, productCountFromGroceryList);
    }


}