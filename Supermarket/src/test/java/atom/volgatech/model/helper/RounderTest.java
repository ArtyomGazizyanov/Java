package atom.volgatech.model.helper;

import org.apache.derby.impl.store.raw.xact.EscalateContainerKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RounderTest {
    @Test
    void roundPositiveNumber() {
        assertEquals(1.78, Rounder.round(1.77896, 2));
    }

    @Test
    void roundNegativeNumber() {
        assertEquals(-1.78, Rounder.round(-1.77896, 2));
    }

    @Test
    void roundIlligalRoundPostitionNumber() {
        try{
            Rounder.round(1.77896, -2);
            assert false;
        } catch(IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
        assertEquals(1.78, Rounder.round(1.77896, 2));
    }

}