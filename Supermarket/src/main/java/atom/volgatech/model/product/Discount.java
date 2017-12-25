package atom.volgatech.model.product;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class Discount implements DiscountInterface {
    Discount(double discount) throws IllegalArgumentException {
        if(discount < 0 )
            throw new IllegalArgumentException("Discount c`nt be less than 0");
        _discount = discount;
    }

    public double get()
    {
        return _discount;
    }

    public boolean isExist() {
        return _discount != 0;
    }

    public boolean equals(double comparer)
    {
        return _discount == comparer;
    }

    private double _discount;
}
