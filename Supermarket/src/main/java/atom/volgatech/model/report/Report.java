package atom.volgatech.model.report;

import atom.volgatech.model.product.Product;
import atom.volgatech.model.report.PeriodReport;

import java.util.HashMap;
import java.util.Map;

public class Report {
    public Report() {
    }

    public static Map<Product, Integer> todayProductList =
            new HashMap<Product, Integer>();

    public static Map<Product, Integer> leftProductsList =
            new HashMap<Product, Integer>();

    public void increaseCustomersAmount()
    {
        _amountOfCustomers++;
    }

    public int getCustomersAmount()
    {
        return _amountOfCustomers;
    }

    public void updateTodaySum(PeriodReport additional) {
        _todaySum += additional.get();
    }

    public double getTodayProfit()
    {
        return _todaySum;
    }

    private float _todaySum = 0;
    private int _amountOfCustomers = 0;
}
