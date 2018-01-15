package atom.volgatech.model.consumer;

import atom.volgatech.model.cash_desk.CashDesk;
import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.simulation.SupermarketSimulation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

    protected BlockingQueue<Customer>  queue = null;
    protected ExecutorService executor = null;

    public Consumer(BlockingQueue<Customer> queue, ExecutorService executor ) {
        this.queue = queue;
        this.executor = executor;
    }

    public void run() {
        Customer customer;
        CashDesk cashDesk = new CashDesk();
        do {
            try {
                customer = queue.take();
                synchronized (SupermarketSimulation.gettodayReport()) {
                    cashDesk.processPoolQueue(customer);
                    SupermarketSimulation.gettodayReport().updateTodaySum(cashDesk.getPeriodReport());
                }
            } catch (InterruptedException ie) {
            }
        } while (!queue.isEmpty());
    }
}
