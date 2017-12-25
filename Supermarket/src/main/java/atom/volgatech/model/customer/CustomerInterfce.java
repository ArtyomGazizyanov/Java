package atom.volgatech.model.customer;

import atom.volgatech.model.basket.Basket;
import atom.volgatech.model.cash_desk.PaymentMethod.Method;
import atom.volgatech.model.customer.Customer.CustomerKind;

public interface CustomerInterfce {
    Basket getBasket();
    boolean isChooseSomething();
    void getProducts();
    CustomerKind getKind();
    Integer getId();
    Method getPaymentMethod();
}
