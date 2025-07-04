package com.FawryRiseJourney.Customer;

import com.FawryRiseJourney.payment.PaymentInterface;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<ProductInCart> cart;

    public Cart(PaymentInterface payment) {
        this.cart = new ArrayList<ProductInCart>();
    }

    public boolean checkout(PaymentInterface payment) {
        double totalPrice = totalPrice();
        if (payment.charge(totalPrice)) {
            for (ProductInCart product : cart) {
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
        for (ProductInCart product : cart) {
            totalPrice += product.getTotalPrice();
        }
        return totalPrice;
    }

    public double totalWeight() {
        double totalWeight = 0;
        for (ProductInCart product : cart) {
            totalWeight += product.getTotalWeight();
        }
        return totalWeight;
    }

    public double totalShippingCost() {
        double totalShippingCost = 0;
        for (ProductInCart product : cart) {
            totalShippingCost += product.getTotalShippingCost();
        }
        return totalShippingCost;
    }
}
