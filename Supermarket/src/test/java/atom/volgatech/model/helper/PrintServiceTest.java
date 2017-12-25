package atom.volgatech.model.helper;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandlerImpl;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PrintServiceTest {
    @Test
    void setUpInvalidTime() {
        try {
            PrintService.setUp("6:asdasdasdasdaf3ew2312w");
            assert false;
        } catch(Exception e) {
            assertEquals(ParseException.class, e.getClass());
        }
    }

    @Test
    void setUpValidTime() {
        try {
            PrintService.setUp("6:00:00");
        } catch(Exception e) {
            assert false;
        }
    }

    @Test
    void updatePassedValidTime() {
        try {
            PrintService.updatePassedTime(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updatePassedInvalidTime() {
        try {
            PrintService.updatePassedTime(-1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTimeAndUpdate() {
    }

}