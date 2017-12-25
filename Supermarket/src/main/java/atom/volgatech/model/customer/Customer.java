package atom.volgatech.model.customer;

import atom.volgatech.model.cash_desk.PaymentMethod;
import atom.volgatech.model.helper.PrintService;
import atom.volgatech.model.product.Product;
import atom.volgatech.model.supermarket.ShopAssistant;
import atom.volgatech.model.basket.Basket;

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

            if(!ShopAssistant.isCustomerAbleToBuyProduct(_kind, choosenProduct)) {
                System.out.printf("`Customer %1s` being a child tried to pick up %-20s: %1s\n", _id, choosenProduct.getKind().toString(), PrintService.getTimeAndUpdate());
                continue;
            }

            System.out.printf("`Customer%1s` picked up %-20s: %1s\n", _id, choosenProduct.getKind().toString(), PrintService.getTimeAndUpdate());
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

    private int _maxAmountOfProduts = 10;
    private Basket _basket;
    private CustomerKind _kind;
    private Integer _id;
    private PaymentMethod.Method _paymentMethod;
    private Map<Product, Integer> _goodsStock;
}
