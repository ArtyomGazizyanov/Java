package atom.volgatech.model.producer;

import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.helper.PrintService;
import atom.volgatech.model.simulation.SupermarketSimulation;

import java.text.ParseException;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static atom.volgatech.model.simulation.SupermarketSimulation.IsWorkingDayEnd;

public class Producer implements Runnable{

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }
    public void run() {

        try {
            synchronized (SupermarketSimulation._semaphoreProductListChanged) {
                Customer customer = SupermarketSimulation.EnterCustomer(SupermarketSimulation.gettodayReport().getCustomersAmount());

                customer.getProducts();
                if (!customer.getBasket().isEmpty()) {
                    System.out.printf("Customer `Customer %1s` had finished product choosing, now waiting at cash desk: %1s\n\n", customer.getId(), PrintService.getTimeAndUpdate());
                } else {
                    System.out.printf("Customer `Customer %1s` hadn`t choose anything: %1s\n\n", customer.getId(), PrintService.getTimeAndUpdate());
                }

                queue.put(customer);
                SupermarketSimulation.cleanProductList(SupermarketSimulation.gettodayReport().leftProductsList);
            }

            synchronized (SupermarketSimulation._semaphoreTimeChanged)
            {
                SupermarketSimulation.increaseWorkingDayTime(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            assert false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
