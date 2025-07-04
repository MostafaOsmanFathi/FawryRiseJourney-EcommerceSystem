package com.FawryRiseJourney.model.Customer;

import com.FawryRiseJourney.model.payment.PaymentInterface;
import com.FawryRiseJourney.model.product.DigitalProduct;
import com.FawryRiseJourney.model.product.Product;
import com.FawryRiseJourney.model.product.ShippableProduct;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<ProductInCart> listOfProductsInCart;

    public Cart() {
        this.listOfProductsInCart = new ArrayList<ProductInCart>();
    }

    public boolean checkout(PaymentInterface payment) {
        double totalPrice = totalPrice();
        if (payment.charge(totalPrice)) {
            for (ProductInCart product : listOfProductsInCart) {
                product.applyTransaction();
            }
            return true;
        }
        return false;
    }

    public double totalPrice() {
        return totalPriceWithoutShippingCost() + totalShippingCost();
    }

    public double totalPriceWithoutShippingCost() {
        double totalPrice = 0;
        for (ProductInCart product : listOfProductsInCart) {
            totalPrice += product.getTotalPrice();
        }
        return totalPrice;
    }

    public double totalWeight() {
        double totalWeight = 0;
        for (ProductInCart product : listOfProductsInCart) {
            totalWeight += product.getTotalWeight();
        }
        return totalWeight;
    }

    public double totalShippingCost() {
        double totalShippingCost = 0;
        for (ProductInCart product : listOfProductsInCart) {
            totalShippingCost += product.getTotalShippingCost();
        }
        return totalShippingCost;
    }

    public boolean addProduct(Product product, int quantity) {
        if (quantity >= product.getQuantity())
            return false;
        boolean tmp = listOfProductsInCart.add(new ProductInCart(product, quantity));
        return tmp;
    }

    public boolean removeProduct(Product product, int quantity) {
        int index = listOfProductsInCart.indexOf(product);
        if (index >= 0 && index < listOfProductsInCart.size()) {
            listOfProductsInCart.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Shippable Products: \n");

        int idx = 1;
        for (ProductInCart product : listOfProductsInCart) {
            if (product.getProduct() instanceof ShippableProduct shippableProduct) {
                stringBuilder.append(idx + ": " + product.toString() + "\n");
                ++idx;
            }
        }
        stringBuilder.append("Total Weight: " + totalWeight() + "\n\n");

        stringBuilder.append("Digital Products: \n");
        idx = 1;
        for (ProductInCart product : listOfProductsInCart) {
            if (product.getProduct() instanceof DigitalProduct digitalProduct) {
                stringBuilder.append(idx + ": " + product.toString() + "\n");
                ++idx;
            }
        }

        stringBuilder.append("\nsubtotal:\t\t" + totalPriceWithoutShippingCost() + "\n");
        stringBuilder.append("shipping:\t\t" + totalShippingCost() + "\n");
        stringBuilder.append("amount:\t\t" + totalPrice() + "\n");

        stringBuilder.append("------------------");


        return stringBuilder.toString();
    }
}
