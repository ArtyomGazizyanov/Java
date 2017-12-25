package atom.volgatech.model.cash_desk;

import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.simulation.SupermarketSimulation;
import atom.volgatech.model.supermarket.Supermarket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashDeskTest {

    private static Supermarket supermarket = new Supermarket();
    private static SupermarketSimulation supermarketSimulation = new SupermarketSimulation(supermarket);

    CashDesk cashDesk = new CashDesk();
    Customer customer = new Customer(Customer.CustomerKind.Adult, supermarketSimulation.gettodayReport().todayProductList, 0, SupermarketSimulation.generatePaymentMethod());

    @Test
    void processPoolQueueCustomerChooseNothing() {
        try{
            cashDesk.processPoolQueue(customer);
        }catch (Exception e) {
            assert false;
        }
    }

    @Test
    void processPoolQueueCustomerChooseSomething() {
        try{
            customer.getProducts();
            cashDesk.processPoolQueue(customer);
        }catch (Exception e) {
            assert false;
        }
    }

    @Test
    void getPeriodReport() {
        assertNotNull(cashDesk.getPeriodReport());
    }

    @Test
    void addCustomerToQueue() {
    }

}