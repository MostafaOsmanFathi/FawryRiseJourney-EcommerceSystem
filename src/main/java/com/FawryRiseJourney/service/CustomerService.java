package com.FawryRiseJourney.service;

import com.FawryRiseJourney.model.Customer.Cart;
import com.FawryRiseJourney.model.Customer.Customer;
import com.FawryRiseJourney.model.payment.PaymentInterface;
import com.FawryRiseJourney.model.product.Product;

public class CustomerService {
    private static CustomerService customerService;

    private CustomerService() {

    }

    public static CustomerService getCustomerService() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    Customer createCustomer(int customerId, String customerName, String customerEmail) {
        Customer customer = new Customer(customerName, customerEmail);
        customer.setCustomerId(customerId);
        return customer;
    }

    void addPaymentInterface(Customer customer, PaymentInterface paymentInterface) {
        customer.addPaymentMethod(paymentInterface);
    }

    boolean addProductToCart(Customer customer, Product product, int quantity) {
        Cart cart = customer.getCart();
        return cart.addProduct(product, quantity);
    }

    void cartOverView(Customer customer) {
        System.out.println("------Cart Overview------");
        System.out.println("-------------------------");
        System.out.println(customer.getCart());
        System.out.println("-------------------------");
    }

    boolean checkOutCart(Customer customer) {
        return customer.checkoutCart();
    }
}
