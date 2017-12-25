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
                SupermarketSimulation._semaphoreTodayReport.acquire();
                cashDesk.processPoolQueue(customer);
                SupermarketSimulation.gettodayReport().updateTodaySum(cashDesk.getPeriodReport());
                SupermarketSimulation._semaphoreTodayReport.release();
            } catch (InterruptedException ie) {
            }
        } while (!SupermarketSimulation.IsWorkingDayEnd());

        try{
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            queue.clear();
            executor.shutdownNow();
        }
    }
}
