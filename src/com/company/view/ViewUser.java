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
import java.util.ArrayList;
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
                customers.getBillingAddress());

        scanner = new Scanner(System.in);

        controlOrders.addOrder(orders);

    }

    public void meniu() {
        System.out.println("=================== User Menu ===================");
        System.out.println("Press 1 to see all products");
        System.out.println("Press 2 to add a product to your cart");
        System.out.println("Press 3 to see your cart");
        System.out.println("Press 4 to edit the cart");
        System.out.println("Press 5 to remove a product from cart");
        System.out.println("Press 6 to save the cart");
    }

    public void play() {
        boolean running = true;
        meniu();
        while (running) {

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 0 :
                    running = false;
                    break;
                case 1:
                    controlProducts.afisare();
                    break;
                case 2:
                    addCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    editCart();
                    break;
                case 5:
                    removeFromCart();
                    break;
                case 6:
                    saveCart();
                    break;
                default:
                    meniu();
                    break;
            }
        }
    }

    public void addCart() {
        System.out.println("Type the product name: ");
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
        ArrayList<OrderDetails> details = controlOrderDetails.viewOrder(controlOrderDetails.getOrderDetails(orders.getId()).getOrderID());

        String text = "In cos avem: \n";

        for (OrderDetails od :  details){
            Products product = controlProducts.getProduct(od.getProductID());

            text += product.getName();
            text += "\n" + od.getQuantity() + " bucati";
            text += "\n" + "in valoare de " + od.getPrice() + "\n";
        }
        System.out.println(text);
    }

    public void editCart() {

        ArrayList<OrderDetails> details = controlOrderDetails.viewOrder(controlOrderDetails.getOrderDetails(orders.getId()).getOrderID());

        System.out.println("enter product name:");
        String nameProduct = scanner.nextLine();
        System.out.println("enter the new quantity");
        int quantityN = Integer.parseInt(scanner.nextLine());

        if (controlOrderDetails.getOrderDetails(orders.getId()).getProductID() == controlProducts.getProductname(nameProduct).getId()) {

            controlOrderDetails.updateODquantity(controlOrderDetails.getOrderDetails(orders.getId()).getId(), quantityN);

            controlOrderDetails.updateODprice(controlOrderDetails.getOrderDetails(
                            orders.getId()).getId(),
                    controlProducts.getProductname(nameProduct).getPrice() * quantityN);
        }
    }

    public void removeFromCart() {

        ArrayList<OrderDetails> detalii = controlOrderDetails.viewOrder(controlOrderDetails.getOrderDetails(orders.getId()).getOrderID());

        System.out.println("enter the product name:");
        String nameP = scanner.nextLine();

        System.out.println(detalii);

        for (int i = 0; i < detalii.size(); i++) {
            if (detalii.contains(controlProducts.getProductname(nameP))){
                detalii.remove(controlOrderDetails.getOrderDetails(i).getId());
            }
        }

    }

    public void saveCart(){
        System.out.println("enter the shipping address:");
        String shipAddress = scanner.nextLine();

        Orders order1 = new Orders(
                controlOrders.nextId(),
                orders.getCustomerID(),
                controlOrderDetails.orderAmmount(orders.getId()),
                shipAddress,
                orders.getOrderAddress()
        );
        controlOrders.addOrder(order1);
        controlOrders.salvare();
    }

}
