package atom.volgatech.model.product;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class Product implements ProductInterface {
    public enum ProductType {
        Alcohol,
        Cigarettes,
        Food,
        Drinks,
        HouseChemicals,
        Snakes
    }

    public Product(ProductType type, String kind, double cost, double discount) throws InvalidArgumentException {
        _type = type;
        _kind = kind;
        _cost = cost;
        _discount = new Discount(discount);
    }

    public ProductType getType() {
        return _type;
    }

    public String getKind() {
        return _kind;
    }

    public double getCost() {
        return _cost;
    }

    public double getDiscount() {
        return _discount.get();
    }

    public Boolean hasDiscount()
    {
        return _discount.isExist();
    }

    private ProductType _type;
    private String _kind;
    private double _cost;
    private Discount _discount;
}
