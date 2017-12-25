package atom.volgatech.model.simulation;

import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.supermarket.Supermarket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketSimulationTest {
    SupermarketSimulation globalSupermarketSimulation;
    @BeforeEach
    void setUp(){
        Supermarket supermarket = new Supermarket();
        new SupermarketSimulation(supermarket);
    }


    @Test
    void constructor() {
        try {
            Supermarket supermarket = new Supermarket();
            new SupermarketSimulation(supermarket);
        } catch(Exception e) {
            assert false;
        }
    }

    @Test
    void constructorNulParam() {
        try {
            new SupermarketSimulation(null);
            assert false;
        } catch(Exception e) {
        }
    }

    @Test
    void processCustomersPool() {
    }

    @Test
    void generatePaymentMethod() {
        assertNotNull(globalSupermarketSimulation.generatePaymentMethod());
    }

    @Test
    void generateProductPrice() {
        assertNotNull(globalSupermarketSimulation.generateProductPrice());
    }

    @Test
    void increaseWorkingDayTime() {
    }

    @Test
    void isWorkingDayEnd() {
    }

    @Test
    void getMaxAmmountOfCustomers() {
        assertNotNull(globalSupermarketSimulation.getMaxAmmountOfCustomers());
    }

    @Test
    void enterCustomer() {
        Customer customer = globalSupermarketSimulation.EnterCustomer(1);
        assertNotNull(customer);
    }

    @Test
    void generateCustomerKind() {
        Customer.CustomerKind customerKind = globalSupermarketSimulation.generateCustomerKind();
        assertNotNull(customerKind);
    }

    @Test
    void cleanProductList() {
    }

    @Test
    void getReport() {
    }

    @Test
    void gettodayReport() {
    }

    @Test
    void getMaxproductCost() {
    }

    @Test
    void getMinproductCost() {
    }

    @Test
    void getMinProductAmount() {
    }

    @Test
    void getMaxProductAmount() {
    }

    @Test
    void getMaxproductDiscount() {
    }

    @Test
    void getMinproductDiscount() {
    }

    @Test
    void getStartSupermarketTime() {
    }

    @Test
    void isIsFinished() {
    }

}