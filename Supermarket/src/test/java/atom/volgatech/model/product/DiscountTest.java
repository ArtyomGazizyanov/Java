package atom.volgatech.model.product;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {
    @Test
    void constructor() {
        try {
            new Discount(2);
        } catch (IllegalArgumentException e) {
            assert  false;
        }
    }

    @Test
    void constructorInvalidParameter() {
        Discount discount = null;
        try {
            discount = new Discount(-1);
            assert  false;
        } catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void get() {
        Discount discount = null;
        try {
            discount = new Discount(2);
        } catch (IllegalArgumentException e) {
            assert  false;
        }
        assertEquals(2, discount.get());
    }

    @Test
    void isExist() {
        Discount discount = null;
        try {
            discount = new Discount(0);
            assertFalse(discount.isExist());
        } catch (IllegalArgumentException e) {
            assert  false;
        }
    }

    @Test
    void equals() {
        Discount discount = null;
        try {
            discount = new Discount(11);
            assertTrue(discount.equals(11));
        } catch (IllegalArgumentException e) {
            assert  false;
        }
    }

}