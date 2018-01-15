package atom.volgatech.model.consumer;

import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.simulation.SupermarketSimulation;
import atom.volgatech.model.supermarket.Supermarket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerTest {
    Supermarket supermarket = new Supermarket();
    SupermarketSimulation supermarketSimulation = new SupermarketSimulation(supermarket);
    BlockingQueue<Customer> bq = new ArrayBlockingQueue<Customer>(1000);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    Runnable consumer = new Consumer(bq, executor);
    Customer customer0 = supermarketSimulation.EnterCustomer(0);
    Customer customer1 = supermarketSimulation.EnterCustomer(1);
    @Test
    void run() {
        try {
            customer0.getProducts();
            customer1.getProducts();
            bq.add(customer0);
            bq.add(customer1);
            Future<?> submit = executor.submit(consumer);
            while(!submit.isDone()){}
        } catch (Exception e)
        {
            fail(e);
        }
    }

    @Test
    void runWithoutReturnedProducts() {
        try {
            customer0.getProducts();
            customer1.getProducts();
            bq.add(customer0);
            bq.add(customer1);
            Future<?> submit = executor.submit(consumer);
            bq.add(customer1);
            assertFalse(supermarketSimulation.shutDownExecutorWithReturnedProducts(executor, bq));
            bq.add(customer1);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void runmanyThreads() {
        try {
            customer0.getProducts();
            customer1.getProducts();
            bq.add(customer0);
            bq.add(customer1);
            Future<?> submit = executor.submit(consumer);
            Future<?> submit2 = executor.submit(consumer);
        } catch (Exception e)
        {
            fail(e);
        }
    }
}