package atom.volgatech.model.supermarket;

import atom.volgatech.model.simulation.SupermarketSimulation;
import atom.volgatech.model.product.Product;
import atom.volgatech.model.product.ProductKind;
import atom.volgatech.model.report.Report;
import atom.volgatech.model.helper.Rounder;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

public class Supermarket {
    public Supermarket() {
        initProductsList();
    }

    public Report getReport() {
        return _todayReport;
    }

    public void initProductsList() {
        System.out.println("Supermarket products is creating...");

        Random randomAmountOfItems = new Random();
        ProductKind allProductKinds = new ProductKind();

        for (Product.ProductType productType : Product.ProductType.values()) {
            for (String productKind : allProductKinds.allKinds.get(productType) ) {
                Product newProduct = null;
                try {
                    newProduct = new Product(productType, productKind,
                            SupermarketSimulation.generateProductPrice(),
                            generateDiscount()
                            );
                } catch (InvalidArgumentException e) {
                    e.printStackTrace();
                }

                _todayReport.todayProductList.put(newProduct, randomAmountOfItems.nextInt(SupermarketSimulation.getMaxProductAmount() - SupermarketSimulation.getMinProductAmount()) + SupermarketSimulation.getMinProductAmount());
                System.out.printf("%-1s (cost: %.2f, discount: %.2f) - %-1d\n",
                        productKind, newProduct.getCost(), newProduct.getDiscount(),
                        _todayReport.todayProductList.get(newProduct));
            }

        }

        _todayReport.leftProductsList.putAll(_todayReport.todayProductList);
        System.out.println("Supermarket products have been successfully formed!");
        System.out.println();
    }

    private double generateDiscount() {
        Random doDiscount = new Random();
        double generatedDiscount = doDiscount.nextBoolean() ?
                SupermarketSimulation.getMinproductDiscount() + (doDiscount.nextDouble() * (SupermarketSimulation.getMaxproductDiscount() - SupermarketSimulation.getMinproductDiscount()))
                : 0;
        return  Rounder.round(generatedDiscount, 2);
    }

    private Report _todayReport = new Report();
}
