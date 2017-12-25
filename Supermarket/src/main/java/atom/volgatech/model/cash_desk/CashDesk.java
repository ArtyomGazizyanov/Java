package atom.volgatech.model.cash_desk;

import atom.volgatech.model.basket.Basket;
import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.customer.Customer.CustomerKind;
import atom.volgatech.model.product.Product;
import atom.volgatech.model.report.PeriodReport;
import atom.volgatech.model.helper.PrintService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class CashDesk implements CashDeskInterface {
    public CashDesk() {
        _customersQueue = new ArrayList<Customer>();
        _periodReport = new PeriodReport();
    }

   /* public void processQueue() {
        if (!_customersQueue.isEmpty()) {

            PrintStartCashDeskProcss();

            for (Customer customer : _customersQueue) {
                if (customer.isChooseSomething()) {

                    Bill bill = new Bill();
                    bill.addToCheque(calculateCustomersBasket(customer, new HashSet<CustomerKind>(){{add(Customer.CustomerKind.Adult);}}));

                    if (!bill.isEmpty()) {
                        _periodReport.add(bill.getBill());
                        System.out.printf("Customer 'Customer%1d' paid %.2f by %1s : %1s\n\n", customer.getId(), bill.getBill(), customer.getPaymentMethod().toString(), PrintService.getTimeAndUpdate());
                    }
                } else {
                    System.out.printf("Customer 'Customer%1d' choose nothing and just left the doors : %1s\n\n", customer.getId(), PrintService.getTimeAndUpdate());
                }
            }
            PrintCashDeskDelimiter();
        }
    }*/

    public void processPoolQueue(Customer customer) {
        PrintStartCashDeskProcss();
        if (customer.isChooseSomething()) {

            Bill bill = new Bill();
            bill.addToCheque(calculateCustomersBasket(customer, new HashSet<CustomerKind>(){{add(Customer.CustomerKind.Adult);}}));

            if (!bill.isEmpty()) {
                _periodReport.add(bill.getBill());
                System.out.printf("Customer 'Customer%1d' paid %.2f by %1s : %1s\n\n", customer.getId(), bill.getBill(), customer.getPaymentMethod().toString(), PrintService.getTimeAndUpdate());
            }
        } else {
            System.out.printf("Customer 'Customer%1d' choose nothing and just left the doors : %1s\n\n", customer.getId(), PrintService.getTimeAndUpdate());
        }
        PrintCashDeskDelimiter();
    }

    public PeriodReport getPeriodReport() {
        return _periodReport;
    }

    /*public void addCustomerToQueue(Customer customer) {
        _customersQueue.add(customer);
    }*/

    private double calculateCustomersBasket(Customer customer, HashSet<CustomerKind> discountTypes) {
        Basket basket = customer.getBasket();
        double result = 0;
        Iterator<Map.Entry<Product, Integer>> iter = basket.getGroceryList().entrySet().iterator();
        while (iter.hasNext()) {
            
            Map.Entry<Product, Integer> entry = iter.next();
            double price = entry.getKey().getCost();
            if (entry.getKey().hasDiscount()) {
                if (discountTypes.contains(customer.getKind())) {
                    System.out.printf("Adult customer %1s a discount for %.2f %% for %1s: %1s\n", customer.getId(),
                            1 - entry.getKey().getDiscount(), entry.getKey().getKind(), PrintService.getTimeAndUpdate());
                    price *= (1 - entry.getKey().getDiscount());
                }
            }
            result += price;
        }

        return result;
    }

    private void PrintStartCashDeskProcss()
    {
        System.out.println("*** Cash desk starts process customers ***");
    }

    private void PrintCashDeskDelimiter()
    {
        System.out.println("************\n");
    }

    private ArrayList<Customer> _customersQueue;
    private PeriodReport _periodReport;
}
