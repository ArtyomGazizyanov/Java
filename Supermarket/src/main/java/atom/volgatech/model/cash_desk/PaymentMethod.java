package atom.volgatech.model.cash_desk;

import java.util.Random;

public class PaymentMethod {
    public enum Method {
        Cash,
        Debit
    }
    public PaymentMethod(Method method){
        _method = method;
    }

    static public Integer metodsCount()
    {
        return Method.values().length;
    }

    static private Method _method;

    public Method getPaymentMethod()
    {
        return _method;
    }
}
