package atom.volgatech.model.cash_desk;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {
    @Test
    void metodsCount() {
        int methodsCount = PaymentMethod.Method.values().length;
        assertEquals((int) methodsCount ,(int) PaymentMethod.metodsCount());
    }

    @Test
    void getPaymentMethod() {
        PaymentMethod paymentMethod = new PaymentMethod(PaymentMethod.Method.Cash);
        assertEquals(paymentMethod.getPaymentMethod(), PaymentMethod.Method.Cash);
    }
}