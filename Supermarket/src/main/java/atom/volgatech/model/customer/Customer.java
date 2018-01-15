package atom.volgatech.model.customer;

import atom.volgatech.model.cash_desk.PaymentMethod;
import atom.volgatech.model.helper.PrintService;
import atom.volgatech.model.product.Product;
import atom.volgatech.model.supermarket.ShopAssistant;
import atom.volgatech.model.basket.Basket;
import javafx.util.Pair;

import java.util.*;

public class Customer implements CustomerInterfce {
    public enum CustomerKind {
        Child,
        Adult,
        Retired
    }

    public Customer(CustomerKind kind, Map<Product, Integer> goodsStock, Integer id, PaymentMethod paymentMethod){
        _goodsStock = goodsStock;
        _kind = kind;
        _basket = new Basket();
        _id = id;
        _paymentMethod = paymentMethod.getPaymentMethod();
    }

    public void setTime(double time) {
        _myTime = time;
    }

    public Basket getBasket() {
        return _basket;
    }

    public boolean isChooseSomething()
    {
        return _basket.getGroceryList().size() != 0;
    }

    public void getProducts() {
        Random amountOfDifferentProductsToBuy = new Random();
        int productsLeft = amountOfDifferentProductsToBuy.nextInt(_maxAmountOfProduts );
        for(Integer i = 0; i < productsLeft; ++i) {
            Random choosenProductRnd = new Random();
            ArrayList<Product> availableProduct = new ArrayList<Product>();
            availableProduct.addAll(_goodsStock.keySet());

            Integer rndPrintNumberOfProductToChoose = choosenProductRnd.nextInt(availableProduct.size());
            Product choosenProduct = availableProduct.get(rndPrintNumberOfProductToChoose);

            if(!ShopAssistant.isNotChild(_kind, choosenProduct)) {
                System.out.printf("`Customer %1s` being a child tried to pick up %-20s\n", _id, choosenProduct.getKind().toString());
                continue;
            }

            System.out.printf("`Customer%1s` picked up %-20s\n", _id, choosenProduct.getKind().toString());
            pickUpProduct(choosenProduct);
            _basket.addItem(choosenProduct);
        }
    }

    public CustomerKind getKind()
    {
        return _kind;
    }

    public Integer getId()
    {
        return _id;
    }

    public PaymentMethod.Method getPaymentMethod()
    {
        return _paymentMethod;
    }

    private int pickUpProduct(Product product) {
        int amountToBuy = 1;
        int boughtProductAmount = 0;

        if(_goodsStock.get(product) <= amountToBuy) {
            boughtProductAmount = _goodsStock.get(product);
            _goodsStock.put(product, 0);
        } else {
            boughtProductAmount = amountToBuy;
            _goodsStock.put(product, _goodsStock.get(product) - amountToBuy);
        }

        return boughtProductAmount;
    }

    public int returnProducts() {
        for(Map.Entry<Product, Integer> entry : _basket.getGroceryList().entrySet()) {
            if(_goodsStock.containsKey(entry.getKey())) {
                _goodsStock.put(entry.getKey(),_goodsStock.get(entry.getKey()) +  entry.getValue());
            } else {
                _goodsStock.put(entry.getKey(), entry.getValue());
            }
        }
        _basket.getGroceryList().clear();
        return 0;
    }

    private int _maxAmountOfProduts = 10;
    private Basket _basket;
    private CustomerKind _kind;
    private Integer _id;
    private PaymentMethod.Method _paymentMethod;
    private Map<Product, Integer> _goodsStock;
    private double _myTime;
}
