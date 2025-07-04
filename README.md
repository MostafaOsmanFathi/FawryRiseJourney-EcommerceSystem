# Fawry Rise Journey Shop

> download jar relaese from here
[JAR](https://github.com/MostafaOsmanFathi/FawryRiseJourney-EcommerceSystem/releases/latest)

---

## ✨ Table of Contents

* [Project Structure](#project-structure)
* [Features](#features)
* [Getting Started](#getting-started)

    * [Prerequisites](#prerequisites)
    * [Running the Application](#running-the-application)
* [Usage](#usage)
* [Classes Overview](#classes-overview)

    * [Main Application](#main-application)
    * [Customer Module](#customer-module)
    * [Payment Module](#payment-module)
    * [Product Module](#product-module)
* [Future Enhancements](#future-enhancements)

---

## 📁 Project Structure

```
C:.
|   Main.java
|   
+---model
|   +---Customer
|   |       Cart.java
|   |       Customer.java
|   |       ProductInCart.java
|   |       
|   +---payment
|   |       PaymentInterface.java
|   |       PseudoPayment.java
|   |       
|   \---product
|           DigitalProduct.java
|           Product.java
|           PseudoShipping.java
|           Shippable.java
|           ShippableProduct.java
|           
\---service
        CustomerService.java
        ProductService.java
```

> **Note:** Unit tests have been implemented for `CustomerService` and `ProductService` to verify their core
> functionalities.

---

## ✨ Features

* **Product Management**: Add and display various types of products (digital and shippable).
* **Customer Management**: Create customers with unique IDs, names, and emails.
* **Shopping Cart**: Add products to cart, view contents, and calculate total price and shipping.
* **Payment Processing**: Simulate payment using a pseudo-payment system.
* **Shipping Calculation**: Based on product weight.
* **Balance Management**: Deposit funds and check customer balance.

---

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK) 11 or higher.

### Running the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/MostafaOsmanFathi/FawryRiseJourney-EcommerceSystem.git
   cd FawryRiseJourney
   ```

2. **Compile the Java files:**
   Navigate to the root directory and run:

   ```bash
   javac Main.java \
         model/Customer/*.java \
         model/payment/*.java \
         model/product/*.java \
         service/*.java
   ```

3. **Run the application:**

   ```bash
   java Main
   ```

> Adjust the compilation command if needed depending on your environment or IDE.

---

## ⚖️ Usage

When the application starts:

1. Enter your name and email.
2. Choose from the menu:

    * Show Products
    * Add Product to Cart
    * View Cart
    * Checkout
    * Exit
    * Deposit Money
    * Show My Balance

---

## 📊 Classes Overview

### Main Application

* **Main.java**: Entry point that initializes products and coordinates user input.

### Customer Module

* **Customer.java**: Represents a customer with ID, name, email, and a cart.
* **Cart.java**: Manages cart contents and handles checkout.
* **ProductInCart.java**: Tracks product quantities and calculates cost and shipping.

### Payment Module

* **PaymentInterface.java**: Defines payment method operations.
* **PseudoPayment.java**: Simulates a payment system using a `HashMap` for balances.

### Product Module

* **Product.java**: Abstract base for all products.
* **DigitalProduct.java**: Extends `Product` with a digital code.
* **ShippableProduct.java**: Adds weight and shipping for physical products.
* **Shippable.java**: Interface for defining shipping behavior.
* **PseudoShipping.java**: Calculates shipping based on weight.

