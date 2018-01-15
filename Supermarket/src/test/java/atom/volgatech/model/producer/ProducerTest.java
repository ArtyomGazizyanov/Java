package atom.volgatech.model.producer;

import atom.volgatech.model.consumer.Consumer;
import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.simulation.SupermarketSimulation;
import atom.volgatech.model.supermarket.Supermarket;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class ProducerTest {
    Supermarket supermarket = new Supermarket();
    SupermarketSimulation supermarketSimulation = new SupermarketSimulation(supermarket);
    BlockingQueue<Customer> bq = new ArrayBlockingQueue<Customer>(1000);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    Runnable producer = new Producer(bq);

    @Test
    void run() {
        Future<?> submit = executor.submit(producer);
    }

    @Test
    void runWithReturnedProducts() {
        Future<?> submit = executor.submit(producer);
        assertTrue(supermarketSimulation.shutDownExecutorWithReturnedProducts(executor, bq));
    }

    @Test
    void runWithManyThreads() {
        Future<?> submit = executor.submit(producer);
        Future<?> submit1 = executor.submit(producer);
        Future<?> submit2 = executor.submit(producer);
        assertTrue(supermarketSimulation.shutDownExecutorWithReturnedProducts(executor, bq));
    }

    @Test
    void runNullQueue() {
        try {
            Runnable producer = new Producer(null);
            Future<?> submit = executor.submit(producer);
            while (!submit.isDone()){}

        } catch (Exception e) {
            fail(e);
        }

    }
    @Test
    void runInterapted() {
        try {
            executor.shutdownNow();
            Future<?> submit = executor.submit(producer);
            assert false;
        } catch (Exception e) {
        }
    }

}