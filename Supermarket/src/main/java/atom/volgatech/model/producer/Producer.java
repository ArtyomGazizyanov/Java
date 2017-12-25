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
        Random randomAmountOfCustomers = new Random();
        while(!SupermarketSimulation.IsWorkingDayEnd())
        {
            Integer newCustomersAmount = randomAmountOfCustomers.nextInt(SupermarketSimulation.getMaxAmmountOfCustomers());
            System.out.println(newCustomersAmount + " Customers arriving at the doors!: " + PrintService.getTimeAndUpdate());

            for(byte i = 0; i < newCustomersAmount; ++i) {

                try {

                    SupermarketSimulation._semaphoreProductListChanged.acquire();
                    Customer customer = SupermarketSimulation.EnterCustomer(SupermarketSimulation.gettodayReport().getCustomersAmount());

                    customer.getProducts();
                    if(!customer.getBasket().isEmpty()){
                        System.out.printf("Customer `Customer %1s` had finished product choosing, now waiting at cash desk: %1s\n\n", customer.getId(), PrintService.getTimeAndUpdate());
                    } else {
                        System.out.printf("Customer `Customer %1s` hadn`t choose anything: %1s\n\n", customer.getId(), PrintService.getTimeAndUpdate());
                    }

                    queue.put(customer);
                    SupermarketSimulation.cleanProductList(SupermarketSimulation.gettodayReport().leftProductsList);

                    SupermarketSimulation._semaphoreProductListChanged.release();

                    SupermarketSimulation._semaphoreTimeChanged.acquire();
                    SupermarketSimulation.increaseWorkingDayTime(5);
                    SupermarketSimulation._semaphoreTimeChanged.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    assert false;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
