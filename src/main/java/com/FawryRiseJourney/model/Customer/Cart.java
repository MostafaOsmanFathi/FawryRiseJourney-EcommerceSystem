package com.FawryRiseJourney.model.Customer;

import com.FawryRiseJourney.model.payment.PaymentInterface;
import com.FawryRiseJourney.model.product.Product;

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
        return listOfProductsInCart.add(new ProductInCart(product, quantity));
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
        for (ProductInCart product : listOfProductsInCart) {
            stringBuilder.append("--------------------------");
            stringBuilder.append(product.toString());
            stringBuilder.append("--------------------------");
        }
        return stringBuilder.toString();
    }
}
