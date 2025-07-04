package com.FawryRiseJourney.payment;

import com.FawryRiseJourney.Customer;

import java.util.HashMap;

public class PseudoPayment implements PaymentInterface {
    static final HashMap<Integer, Double> customerBalance = new HashMap<>();

    private final int customerId;


    public PseudoPayment(int customerId) {
        this.customerId = customerId;
    }

    static boolean createCustomerBalance(Customer customer) {
        if (customerBalance.containsKey(customer.getCustomerId())) {
            return false;//Customer allready exist
        }
        customerBalance.put(customer.getCustomerId(), 0.0);
        return true;
    }

    @Override
    public boolean charge(double amount) {
        if (customerBalance.containsKey(customerId)) {
            throw new IllegalCallerException("Customer doesn't Exist in Our Payment Service please create your cutomer in our servcie ");
        }
        if (amount > customerBalance.get(this.customerId)) {
            return false;
        }

        customerBalance.put(this.customerId, customerBalance.get(this.customerId) - amount);
        return true;
    }

    @Override
    public boolean refund(double amount) {
        //TODO to be Support "Refund" later
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
