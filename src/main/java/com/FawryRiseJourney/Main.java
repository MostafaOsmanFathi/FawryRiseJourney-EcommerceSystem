package com.FawryRiseJourney;

import com.FawryRiseJourney.model.Customer.Cart;
import com.FawryRiseJourney.model.Customer.Customer;
import com.FawryRiseJourney.model.payment.PseudoPayment;
import com.FawryRiseJourney.model.product.DigitalProduct;
import com.FawryRiseJourney.model.product.PseudoShipping;
import com.FawryRiseJourney.model.product.Shippable;
import com.FawryRiseJourney.model.product.ShippableProduct;
import com.FawryRiseJourney.service.CustomerService;
import com.FawryRiseJourney.service.ProductService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductService productService = ProductService.getProductService();
    private static final CustomerService customerService = CustomerService.getCustomerService();

    public static void main(String[] args) {
        initializeProducts();

        System.out.println("Welcome to Fawry Rise Journey Shop");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email:");
        String email = scanner.nextLine();

        Customer customer = customerService.createCustomer(1, name, email);
        PseudoPayment.createCustomerBalance(customer);
        PseudoPayment payment = new PseudoPayment(customer.getCustomerId());
        customer.addPaymentMethod(payment);

        Cart cart = customer.getCart();

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Show Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.println("6. Deposit Money");
            System.out.println("7. Show My Balance");

            System.out.print("Choose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    showProducts();
                    break;
                case 2:
                    addProductToCart(cart);
                    break;
                case 3:
                    System.out.println(cart);
                    break;
                case 4:
                    System.out.println(name + "balance before checkout: " + payment.getBalance(customer.getCustomerId()));
                    if (customer.checkoutCart()) {
                        System.out.println("Checkout is successful");
                    } else {
                        System.out.println("Checkout is unsuccessful");
                    }
                    System.out.println(name + "balance after checkout: " + payment.getBalance(customer.getCustomerId()));
                    break;
                case 5:
                    running = false;
                    break;
                case 6:
                    System.out.print("enter amount to deposit: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    payment.deposit(customer.getCustomerId(), amount);
                    System.out.println("Deposit successful new balance is: " + payment.getBalance(customer.getCustomerId()));
                    break;
                case 7:
                    System.out.print("your balance is: " + payment.getBalance(customer.getCustomerId()));
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }

    private static void initializeProducts() {
        productService.getProductList().clear();

        productService.addProduct(new DigitalProduct("Vodafone Scratch Card", 100.0, 5, "1234-1232-111-55"));
        productService.addProduct(new DigitalProduct("WE Scratch Card", 200, 10, "123674-1252-122-5522"));
        productService.addProduct(new DigitalProduct("Orange Scratch Card", 50.0, 13, "121334-134232-12211-55555"));

        Shippable pseudoShipping = new PseudoShipping(1);
        productService.addProduct(new ShippableProduct("Biscuits", 100.0, 10, LocalDate.of(2027, 3, 12), .25, pseudoShipping));
        productService.addProduct(new ShippableProduct("TV", 100.0, 3, 5, pseudoShipping));
        productService.addProduct(new ShippableProduct("Mobile", 100.0, 3, LocalDate.of(2027, 3, 12), 1, pseudoShipping));
    }

    private static void showProducts() {
        System.out.println("Available Products:");
        var list = productService.getProductList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + i + "] " + list.get(i));
        }
    }

    private static void addProductToCart(Cart cart) {
        showProducts();
        System.out.print("Enter product number to add: ");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        if (index >= 0 && index < productService.getProductList().size()) {
            cart.addProduct(productService.getProductList().get(index), quantity);
            System.out.println("Product added to cart.");
        } else {
            System.out.println("Invalid product number.");
        }
    }
}
