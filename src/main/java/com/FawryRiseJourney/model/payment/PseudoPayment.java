package com.FawryRiseJourney.model.payment;

import com.FawryRiseJourney.model.Customer.Customer;

import java.util.HashMap;

public class PseudoPayment implements PaymentInterface {
    //Replacement for the database for now
    static final HashMap<Integer, Double> customerBalance = new HashMap<>();
    static double faweryProfit = 0.0;
    private final int customerId;


    public PseudoPayment(int customerId) {
        this.customerId = customerId;
    }

    public static boolean createCustomerBalance(Customer customer) {
        if (customerBalance.containsKey(customer.getCustomerId())) {
            return false;//Customer allready exist
        }
        customerBalance.put(customer.getCustomerId(), 0.0);
        return true;
    }

    @Override
    public boolean charge(double amount) {
        if (!customerBalance.containsKey(customerId)) {
            throw new IllegalCallerException("Customer doesn't Exist in Our Payment Service please create your cutomer in our servcie ");
        }
        if (amount > customerBalance.get(this.customerId)) {
            return false;
        }

        customerBalance.put(this.customerId, customerBalance.get(this.customerId) - amount);
        faweryProfit += amount;
        return true;
    }

    public boolean deposit(int customerId, double amount) {
        customerBalance.put(customerId, customerBalance.get(customerId) + amount);
        return true;
    }

    public boolean withdraw(int customerId, double amount) {
        double balance = customerBalance.get(customerId);
        if (balance < amount) {
            return false;
        }
        customerBalance.put(customerId, customerBalance.get(customerId) + amount);
        return true;
    }

    public double getBalance(int customerId) {
        return customerBalance.get(customerId);
    }

    @Override
    public boolean refund(double amount) {
        //TODO to be Support "Refund" later
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
