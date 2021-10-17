package com.company.view;

import com.company.controller.ControlCustomers;
import com.company.controller.ControlOrderDetails;
import com.company.controller.ControlOrders;
import com.company.controller.ControlProducts;
import com.company.models.orders.Customers;
import com.company.models.orders.OrderDetails;
import com.company.models.orders.Orders;
import com.company.models.products.Products;

import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ViewUser {
    private ControlProducts controlProducts;
    private ControlCustomers controlCustomers;
    private ControlOrders controlOrders;
    private ControlOrderDetails controlOrderDetails;
    private Scanner scanner;

    private Orders orders;

    public ViewUser(Customers customers) {
        controlProducts = new ControlProducts(Path.of("src", "com", "company", "resources", "products.txt").toString());
        controlCustomers = new ControlCustomers(Path.of("src", "com", "company", "resources", "customers.txt").toString());
        controlOrders = new ControlOrders(Path.of("src", "com", "company", "resources", "orders.txt").toString());
        controlOrderDetails = new ControlOrderDetails(Path.of("src", "com", "company", "resources", "orderdetails.txt").toString());

        orders = new Orders(
                controlOrders.nextId(),
                customers.getId(),
                0,
                "unknown",
                "unknown");

        scanner = new Scanner(System.in);

        controlOrders.addOrder(orders);

    }

    public void meniu() {
        System.out.println("=================== User Menu ===================");
        System.out.println("Press 1 to see all products");
        System.out.println("Press 2 to add a product to your cart");
        System.out.println("Press 3 to see your cart");
        System.out.println("Press 4 to edit the cart");


    }

    public void play() {

        boolean running = true;

        meniu();
        while (running) {

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0 -> running = false;
                case 1 -> controlProducts.afisare();
                case 2 -> addCart();
                case 3 -> viewCart();
                case 4 -> editCart();
                default -> meniu();
            }
        }
    }

    public void addCart() {
        System.out.println("Type the product's name: ");
        String nameProduct = scanner.nextLine();

        Products chosen = controlProducts.getProductname(nameProduct);

        if (chosen != null) {

            System.out.println("Enter the wanted quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (chosen.getStock() >= quantity) {
                OrderDetails orderDetails = new OrderDetails(
                        controlOrderDetails.nextId(),
                        orders.getId(),
                        chosen.getId(),
                        chosen.getPrice() * quantity,
                        quantity
                );

                controlOrderDetails.addOrderDetails(orderDetails);

                controlProducts.updateStock(chosen.getId(), (chosen.getStock() - quantity));
            } else {
                System.out.println("We do not own the quantity wanted. Check the quantity available.");
            }
        } else {
            System.out.println("The product does not exist. Check product's name.");
        }
    }

    public void viewCart() {
        System.out.println("E-mail: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String pass = scanner.nextLine();

        Customers customer = controlCustomers.getCustomerEmailPass(email, pass);

        if (customer != null) {

            System.out.println("What is the order's id ?");
            int orderID = Integer.parseInt(scanner.nextLine());

            if (controlOrderDetails.getOrderDetails(orderID) != null) {
                // order view by customerID
                controlOrderDetails.viewOrder(orderID);
            } else {
                System.out.println("The order does not exist. Review the order's id");
            }
        } else {
            System.out.println("E-mail or password incorrect.");
        }
    }

    public void editCart() {
        System.out.println("E-mail: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String pass = scanner.nextLine();

        Customers customer = controlCustomers.getCustomerEmailPass(email, pass);

        if (customer != null) {

            System.out.println("What is the order's id ?");
            int orderID = Integer.parseInt(scanner.nextLine());

            if (controlOrders.getOrder(orderID) != null) {
                System.out.println("Enter product's name: ");
                String nameP = scanner.nextLine();

                if (controlProducts.getProductname(nameP) != null) {

                    System.out.println("Enter the wanted quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    if (controlProducts.getProductname(nameP).getStock() >= quantity) {

                        controlOrderDetails.updateODquantity(
                                orderID,
                                quantity);
                        controlOrderDetails.updateODprice(
                                orderID,
                                quantity * controlProducts.getProductname(nameP).getPrice());
                    } else {
                        System.out.println("We don't own this quantity. Check the quantity available.");
                    }

                } else {
                    System.out.println("The product does not exist.");
                }

            } else {
                System.out.println("The order does not exist. Review the order's id");
            }
        } else {
            System.out.println("E-mail or password incorrect.");
        }
    }


}
