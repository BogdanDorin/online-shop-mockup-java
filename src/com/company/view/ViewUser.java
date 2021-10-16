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

    }

    public void play() {

        boolean running = true;

        meniu();
        while (running == true) {

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    controlProducts.afisare();
                    break;
                case 2:
                    addCos();
                    break;
                default:
                    meniu();
                    break;
            }
        }
    }

    public void addCos() {
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

                controlProducts.updateStock(chosen.getId(), chosen.getStock() - quantity);
            } else {
                System.out.println("we do not have this quantity. check the quantity available.");
            }
        } else {
            System.out.println("The product does not exist. Check product's name.");
        }
    }



}
