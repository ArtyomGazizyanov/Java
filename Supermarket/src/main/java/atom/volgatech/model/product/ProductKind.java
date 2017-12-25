package atom.volgatech.model.product;

import atom.volgatech.model.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductKind {
    public static Map<Product.ProductType, List<String>> allKinds =
            new HashMap<Product.ProductType, List<String>>();

    public ProductKind() {
        allKinds.put(Product.ProductType.Alcohol, Stream.of("Vodka", "Beer").collect(Collectors.toList()));
        allKinds.put(Product.ProductType.Cigarettes, Stream.of("Hard Cigarettes pack", "Soft Cigarettes pack").collect(Collectors.toList()));
        allKinds.put(Product.ProductType.Drinks, Stream.of("Lemonade", "Water", "Juice").collect(Collectors.toList()));
        allKinds.put(Product.ProductType.Food, Stream.of("Ham", "Cheese", "Bread").collect(Collectors.toList()));
        allKinds.put(Product.ProductType.HouseChemicals, Stream.of("Shampoo", "Toothpaste", "deodorant").collect(Collectors.toList()));
        allKinds.put(Product.ProductType.Snakes, Stream.of("Crisp`s pack", "Cracker`s pack", "Chocolate bar").collect(Collectors.toList()));
    }
}
