package atom.volgatech.model.supermarket;

import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.product.Product;

public class ShopAssistant {
    ShopAssistant() {
    }

    public static boolean isCustomerAbleToBuyProduct(Customer.CustomerKind customerKind, Product product) {
        switch (product.getType()) {
            case Alcohol:
            case Cigarettes:
                return isCustomerKindAbleToBuyAdultProducts(customerKind);
            default:
                return true;
        }
    }

    private static boolean isCustomerKindAbleToBuyAdultProducts(Customer.CustomerKind customerKind) {
        return customerKind != Customer.CustomerKind.Child;
    }
}
