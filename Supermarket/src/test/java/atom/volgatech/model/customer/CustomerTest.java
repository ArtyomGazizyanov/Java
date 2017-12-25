package atom.volgatech.model.customer;

import atom.volgatech.model.basket.Basket;
import atom.volgatech.model.cash_desk.PaymentMethod;
import atom.volgatech.model.product.Product;
import atom.volgatech.model.simulation.SupermarketSimulation;
import atom.volgatech.model.supermarket.Supermarket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private static Supermarket supermarket = new Supermarket();
    private static SupermarketSimulation supermarketSimulation = new SupermarketSimulation(supermarket);
    private static Customer customer;
    private static Basket basket;

    @BeforeAll
    static void setUp() {
        supermarket.initProductsList();
        customer = new Customer(SupermarketSimulation.generateCustomerKind(), supermarket.getReport().todayProductList,
                0, SupermarketSimulation.generatePaymentMethod());
        customer.getProducts();
        basket = customer.getBasket();
    }
    @Test
    void getKindAdult() {
        Customer customer = new Customer(Customer.CustomerKind.Adult, supermarketSimulation.gettodayReport().todayProductList, 0, SupermarketSimulation.generatePaymentMethod());
        assertEquals(customer.getKind(), Customer.CustomerKind.Adult);
        assertNotNull(basket);
    }

    @Test
    void getKindChild() {
        Customer customer = new Customer(Customer.CustomerKind.Child, supermarketSimulation.gettodayReport().todayProductList, 0, SupermarketSimulation.generatePaymentMethod());
        assertEquals(customer.getKind(), Customer.CustomerKind.Child);
        assertNotNull(basket);
    }

    @Test
    void isChooseSomething() {
        Customer customer = new Customer(Customer.CustomerKind.Child, supermarketSimulation.gettodayReport().todayProductList, 0, SupermarketSimulation.generatePaymentMethod());
        customer.getProducts();
        basket = customer.getBasket();
        if(!basket.isEmpty())
        {
            assertTrue(customer.isChooseSomething());
        } else {
            assertFalse(customer.isChooseSomething());
        }
        assertNotNull(basket);
    }

    @Test
    void getid() {
        Customer customer = new Customer(Customer.CustomerKind.Child, supermarketSimulation.gettodayReport().todayProductList, 0, SupermarketSimulation.generatePaymentMethod());
        assertEquals(0, (int) customer.getId());
    }

    @Test
    void paymentMethod() {
        Customer customer = new Customer(Customer.CustomerKind.Child, supermarketSimulation.gettodayReport().todayProductList, 0, new PaymentMethod(PaymentMethod.Method.Debit));
        assertEquals(PaymentMethod.Method.Debit, customer.getPaymentMethod());
    }

    @Test
    void paymentMethodCash() {
        Customer customer = new Customer(Customer.CustomerKind.Child, supermarketSimulation.gettodayReport().todayProductList, 0, new PaymentMethod(PaymentMethod.Method.Cash));
        assertEquals(PaymentMethod.Method.Cash, customer.getPaymentMethod());
    }
}