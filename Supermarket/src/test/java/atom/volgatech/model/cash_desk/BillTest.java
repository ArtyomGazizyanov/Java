package atom.volgatech.model.cash_desk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {
    @Test
    void constructor() {
        Bill bill = new Bill();
    }

    @Test
    void constructorEmpty() {
        Bill bill = new Bill();
        assertTrue(bill.isEmpty());
    }

    @Test
    void addToCheque() {
        Bill bill = new Bill();
        bill.addToCheque(1);
        assertFalse(bill.isEmpty());
    }

    @Test
    void getBill() {
        Bill bill = new Bill();
        bill.addToCheque(1);
        assertEquals(1, bill.getBill());
    }

    @Test
    void isEmpty() {
    }

}