package com.FawryRiseJourney.service;

import com.FawryRiseJourney.model.Customer.Cart;
import com.FawryRiseJourney.model.Customer.Customer;
import com.FawryRiseJourney.model.payment.PseudoPayment;
import com.FawryRiseJourney.model.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    ProductService productService;
    CustomerService customerService;

    Customer customer1;
    Customer customer2;

    @BeforeEach
    void setUp() {
        productService = ProductService.getProductService();
        productService.getProductList().clear();
        customer1 = null;
        customer2 = null;
        //Digital Product
        productService.addProduct(new DigitalProduct("Vodafone Scratch Card", 100.0, 5, "1234-1232-111-55"));
        productService.addProduct(new DigitalProduct("WE Scratch Card", 200, 10, "123674-1252-122-5522"));
        productService.addProduct(new DigitalProduct("Orange Scratch Card", 50.0, 13, "121334-134232-12211-55555"));


        //Shippable Product
        Shippable pseudoShipping = new PseudoShipping(1);

        productService.addProduct(new ShippableProduct("Biscites", 100.0, 10, LocalDate.of(2027, 3, 12), .25, pseudoShipping));

        productService.addProduct(new ShippableProduct("TV", 100.0, 3, 5, pseudoShipping));
        productService.addProduct(new ShippableProduct("Mobile", 100.0, 3, LocalDate.of(2027, 3, 12), 1, pseudoShipping));

        customerService = CustomerService.getCustomerService();
        customer1 = customerService.createCustomer(1, "Mostafa", "Mostafa@gmail.com");
        customer2 = customerService.createCustomer(2, "Ali", "Ali@gmail.com");
    }

    @Test
    void getCustomerService() {
        assertNotNull(CustomerService.getCustomerService());
    }

    @Test
    void createCustomer() {
        Customer testCustomer = customerService.createCustomer(3, "Ahmed", "Ahmed@gmail.com");
        assertEquals("Ahmed", testCustomer.getName());
        assertEquals("Ahmed@gmail.com", testCustomer.getEmail());
        assertEquals(3, testCustomer.getCustomerId());
    }

    @Test
    void addPaymentInterface() {
        PseudoPayment pseudoPayment = new PseudoPayment(customer1.getCustomerId());
        int beforeTheAddingSize = productService.getProductList().size();
        customerService.addPaymentInterface(customer1, pseudoPayment);
        assertNotEquals(beforeTheAddingSize, customer1.getNumberOfPaymentMethods());
    }

    @Test
    void addProductToCart() {
        Product product = productService.getProductList().getFirst();
        boolean result = customerService.addProductToCart(customer1, product, 1);
        assertTrue(result);

        product = productService.getProductList().get(1);
        result = customerService.addProductToCart(customer1, product, 100);
        assertFalse(result);
    }


    @Test
    void checkOutCart() {
        PseudoPayment.createCustomerBalance(customer1);

        PseudoPayment pseudoPayment = new PseudoPayment(customer1.getCustomerId());
        customer1.addPaymentMethod(pseudoPayment);
        Cart customer1Cart = customer1.getCart();

        pseudoPayment.deposit(customer1.getCustomerId(), 1000.0);

        customer1Cart.addProduct(productService.getProductList().getFirst(), 1);
        customer1Cart.addProduct(productService.getProductList().get(3), 1);
        customer1Cart.addProduct(productService.getProductList().get(5), 2);

        assertTrue(customer1.checkoutCart());
        double expectedTotalPrice = 100.0 + (100.0 + .25) + ((100.0 + 1) * 2);
        assertEquals(1000.0 - expectedTotalPrice, pseudoPayment.getBalance(customer1.getCustomerId()));

    }
}