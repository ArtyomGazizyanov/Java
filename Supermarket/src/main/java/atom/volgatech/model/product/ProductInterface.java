package atom.volgatech.model.product;

public interface ProductInterface {
    Product.ProductType getType();
    String getKind();
    double getCost();
    double getDiscount();
    Boolean hasDiscount();
}
