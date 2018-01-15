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
            synchronized (SupermarketSimulation.gettodayReport().leftProductsList) {
                Customer customer = SupermarketSimulation.EnterCustomer(SupermarketSimulation.gettodayReport().getCustomersAmount());

                customer.getProducts();

                if (!customer.getBasket().isEmpty()) {
                    System.out.printf("Customer `Customer %1s` had finished product choosing, now waiting at cash desk\n\n", customer.getId());
                } else {
                    System.out.printf("Customer `Customer %1s` hadn`t choose anything\n\n", customer.getId());
                }

                try {
                    queue.put(customer);
                } catch (InterruptedException e) {
                    System.out.printf("Customer `Customer %1s` returned products \n", customer.getId());
                    customer.returnProducts();
                }
                SupermarketSimulation.cleanProductList(SupermarketSimulation.gettodayReport().leftProductsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
