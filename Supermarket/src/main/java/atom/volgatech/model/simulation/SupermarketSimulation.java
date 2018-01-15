package atom.volgatech.model.simulation;

import atom.volgatech.model.cash_desk.PaymentMethod;
import atom.volgatech.model.consumer.Consumer;
import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.producer.Producer;
import atom.volgatech.model.product.Product;
import atom.volgatech.model.report.Report;
import atom.volgatech.model.helper.PrintService;
import atom.volgatech.model.helper.Rounder;
import atom.volgatech.model.supermarket.Supermarket;

import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class SupermarketSimulation {
    public SupermarketSimulation(Supermarket supermarket){
        _supermarket = supermarket;
        _todayReport = _supermarket.getReport();
        try {
            PrintService.setUp(_startSupermarketTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void processCustomersPool() throws InterruptedException, ParseException {
        final BlockingQueue<Customer> bq = new ArrayBlockingQueue<Customer>(_maxCustomerQueue);
        final ExecutorService executor = Executors.newFixedThreadPool(_threadCapacity);
        int cashDeskAmount = 0;
        Random randomAmountOfCustomers = new Random();
        Runnable consumer = new Consumer(bq, executor);
        Runnable producer = new Producer(bq);
        while(!SupermarketSimulation.IsWorkingDayEnd()) {

            Integer newCustomersAmount = randomAmountOfCustomers.nextInt(SupermarketSimulation.getMaxAmmountOfCustomers());;
            System.out.println(newCustomersAmount + " Customers arriving at the doors!: " + PrintService.getTimeAndUpdate());

            for(byte i = 0; i < newCustomersAmount; ++i) {
                executor.execute(producer);
            }
            executor.execute(consumer);
            increaseWorkingDayTime(5);
        }
        shutDownExecutor(executor, bq);
        //while(!executor.isShutdown()) {}

        isFinished = true;
    }

    public boolean isAvailableQueeLength(int actualQueueLength, BlockingQueue<Customer> bq){
        return actualQueueLength > 0 && (bq.size() / actualQueueLength) > _maxAmountOfCustomersInCashDeskQueue;
    }

    public void shutDownExecutor(ExecutorService executor, BlockingQueue<Customer> bq){
        executor.shutdown();
        try{
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            if(bq.size() > 0 ) {
                System.out.printf("Customer queue is not empty.\n");
            } else {
                System.out.printf("Customer queue is empty.\n");
            }
            for (Customer customer : bq) {
                customer.returnProducts();
                System.out.printf("Customer # %d returned all products and left the doors\n", customer.getId());
            }
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("shutdown executor");
            }
            bq.clear();
            executor.shutdownNow();
        }
    }

    public static PaymentMethod generatePaymentMethod()
    {
        Random rnd= new Random();
        Integer chosenPaymentMethod =  rnd.nextInt( PaymentMethod.metodsCount() );
        return new PaymentMethod(PaymentMethod.Method.values()[chosenPaymentMethod ]);
    }

    public static double generateProductPrice() {
        Random randomProductPrice = new Random();
        double generatedPrice = SupermarketSimulation.getMinproductCost() + (randomProductPrice.nextDouble() *
                (SupermarketSimulation.getMaxproductCost() - SupermarketSimulation.getMinproductCost()));
        return Rounder.round(generatedPrice, 2);
    }

    public static void increaseWorkingDayTime(Integer passedTimeInMins) throws ParseException {
        _workingPeriod += (double) passedTimeInMins/60;
        PrintService.updatePassedTime(_workingPeriod);
    }

    public static boolean IsWorkingDayEnd() {
        return _workingPeriod > _workingDayInHours;
    }

    public static Integer getMaxAmmountOfCustomers() {
        return _maxAmountOfCustomers;
    }

    public static Customer EnterCustomer(Integer customerId) {
        _todayReport.increaseCustomersAmount();
        return new Customer(SupermarketSimulation.generateCustomerKind(), _todayReport.leftProductsList, customerId, generatePaymentMethod());
    }

    public static Customer.CustomerKind generateCustomerKind() {
        Random randomCustomerKind = new Random();
        Customer.CustomerKind[] CustomerKindList = Customer.CustomerKind.values();
        return CustomerKindList[randomCustomerKind.nextInt(CustomerKindList.length)];
    }

    public static void cleanProductList(Map<Product, Integer> products) {
        Iterator<Map.Entry<Product, Integer>> iter = products.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Product, Integer> entry = iter.next();
            if(entry.getValue() == 0){
                iter.remove();
            }
        }
    }

    public Report getReport() throws InterruptedException {
        System.out.printf("Today came %-5s customers\n", _todayReport.getCustomersAmount());
        System.out.printf("Today`s profit is %.2f $\n", Rounder.round(_todayReport.getTodayProfit(), 2));

        getCompareTodayProductList();
        return _todayReport;
    }

    public static Report gettodayReport()
    {
        return _todayReport;
    }

    public static double getMaxproductCost() {
        return _maxproductCost;
    }

    public static double getMinproductCost() {
        return _minproductCost;
    }

    public static Integer getMinProductAmount() {
        return _minProductAmount;
    }

    public static Integer getMaxProductAmount() {
        return _maxProductAmount;
    }

    public static double getMaxproductDiscount() {
        return _maxproductDiscount;
    }

    public static double getMinproductDiscount() {
        return _minproductDiscount;
    }

    public static String getStartSupermarketTime() {return _startSupermarketTime;}

    public static boolean isIsFinished() {

        while (!isFinished){}
        return true;
    }
    public static Semaphore _semaphoreTimeChanged = new Semaphore(1);
    public static Semaphore _semaphoreProductListChanged = new Semaphore(1);
    public static Semaphore _semaphoreTodayReport = new Semaphore(1);

    private void getCompareTodayProductList() {
        System.out.printf("%-20s %-10s %s\n", "Product", "Price", "Amount");
        Iterator<Map.Entry<Product, Integer>> iter = _todayReport.todayProductList.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Product, Integer> entry = iter.next();
            if(_todayReport.leftProductsList.containsKey(entry.getKey())) {
                System.out.printf("%-20s| %-10.2f| %d|\n", entry.getKey().getKind().toString(), entry.getKey().getCost(), (entry.getValue() - _todayReport.leftProductsList.get(entry.getKey())));
            }
            else {
                System.out.printf("%-20s| %-10.2f| %d|\n", entry.getKey().getKind().toString(), entry.getValue());
            }
        }
    }

    private static String _startSupermarketTime = "6:00:00";
    private static double _workingDayInHours = 1;
    private static double _workingPeriod = 0;
    private static Integer _maxAmountOfCustomers = 5;
    private static Integer _maxAmountOfCustomersInCashDeskQueue = 2;
    private Supermarket _supermarket;

    private static boolean isFinished = false;
    private static double _maxproductCost = 8000.0;
    private static double _minproductCost = 20.0;
    private static Integer _minProductAmount = 300;
    private static Integer _maxProductAmount = 1000;
    private static double _maxproductDiscount = 0.5;
    private static double _minproductDiscount = 0.1;
    private static Integer _threadCapacity = 2;
    private static Integer _maxCustomerQueue = 1000;
    private static Report _todayReport = new Report();
}
