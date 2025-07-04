package com.FawryRiseJourney.model.Customer;

import com.FawryRiseJourney.model.payment.PaymentInterface;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerId;
    private String name;
    private String email;
    private List<PaymentInterface> paymentMethods;
    private final Cart cart;
    private int defaultPaymentIdx;

    public Customer() {
        this.cart = new Cart();
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.paymentMethods = new ArrayList<PaymentInterface>();
        this.defaultPaymentIdx = -1;
        this.cart = new Cart();
    }

    public void addPaymentMethod(PaymentInterface paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        if (defaultPaymentIdx == -1) {
            defaultPaymentIdx = 0;
        }
    }

    public Cart getCart() {
        return cart;
    }

    public boolean checkoutCart() {
        return cart.checkout(paymentMethods.get(defaultPaymentIdx));
    }

    boolean charge(double amount, int paymentMethodIdx) {
        return paymentMethods.get(paymentMethodIdx).charge(amount);
    }

    boolean charge(double amount) {
        return charge(amount, defaultPaymentIdx);
    }

    boolean refund(double amount, int paymentMethodIdx) {
        return paymentMethods.get(paymentMethodIdx).refund(amount);
    }

    boolean refund(double amount) {
        return refund(amount, defaultPaymentIdx);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDefaultPaymentIdx() {
        return defaultPaymentIdx;
    }

    public void setDefaultPaymentIdx(int defaultPaymentIdx) {
        this.defaultPaymentIdx = defaultPaymentIdx;
    }
}
