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
    private Customers customer;

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

        customer = customers;

        scanner = new Scanner(System.in);

        controlOrders.addOrder(orders);
    }

    public void meniu() {
        System.out.println("\n=================== User Menu ===================");
        System.out.println("Press 1 to see all products");
        System.out.println("Press 2 to add a product to your cart");
        System.out.println("Press 3 to see your cart");
        System.out.println("Press 4 to edit the cart");
        System.out.println("Press 5 to remove a product from cart");
        System.out.println("Press 6 to save the cart");
        System.out.println("==================================================");
        System.out.println("Press 7 to edit the profile");
        System.out.println("==================================================");
    }

    public void profileMenu(){
        System.out.println("\n=================== Profile Menu ===================");
        System.out.println("Use 1 to edit the name");
        System.out.println("Use 2 to edit the email");
        System.out.println("Use 3 to edit the password");
        System.out.println("Use 4 to edit the billing address");
        System.out.println("Use 5 to view your profile");
        System.out.println("==================================================");
    }


    public void play() {
        boolean running = true;
        meniu();
        while (running) {

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 0:
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
                case 7:
                    profile();
                    break;
                default:
                    meniu();
                    break;
            }
        }
    }

    public void profile(){
        boolean ok = true;
        profileMenu();
        while (ok == true){
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 0 :
                    ok = false;
                    break;
                case 1:
                    editName();
                    break;
                case 2:
                    editEmail();
                    break;
                case 3:
                    editPassword();
                    break;
                case 4:
                    editBillingAddress();
                    break;
                case 5:
                    viewProfile();
                    break;
                default:
                    profileMenu();
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

        String text = "Your cart: \n";

        for (OrderDetails od : details) {
            Products product = controlProducts.getProduct(od.getProductID());

            text += "-> " + product.getName();
            text += "\t" + od.getQuantity() + " pieces";
            text += "\t" + "price of " + od.getPrice() + "\n";
        }
        System.out.println(text);
    }

    public void editCart() {

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

        Products idprodus = controlProducts.getProductname(nameP); // id produs

        for (int i = 0; i <= detalii.size(); i++) {
            if (detalii.get(i).getProductID() == idprodus.getId()) {
                detalii.remove(i);
            }
        }

    }

    public void saveCart() {
        System.out.println("enter the shipping address:");
        String shipAddress = scanner.nextLine();

        Orders order1 = new Orders(
                controlOrders.nextId(),
                orders.getCustomerID(),
                controlOrderDetails.orderAmount(orders.getId()),
                shipAddress,
                orders.getOrderAddress()
        );
        controlOrders.addOrder(order1);
        controlOrders.salvare();
    }

    //edit name email pass billing address

    public void editName(){
        System.out.println("enter the new name:");
        String newName = scanner.nextLine();

        controlCustomers.updateName(customer.getId(), newName);
        controlCustomers.salvare();
    }

    public void editEmail(){
        System.out.println("enter the new email:");
        String newEmail = scanner.nextLine();

        controlCustomers.updateEmail(customer.getId(), newEmail);
        controlCustomers.salvare();
    }

    public void editPassword(){
        System.out.println("enter the new password:");
        String newPassword = scanner.nextLine();

        controlCustomers.updatePassword(customer.getId(), newPassword);
        controlCustomers.salvare();
    }

    public void editBillingAddress(){
        System.out.println("enter the new address:");
        String newAddress = scanner.nextLine();

        controlCustomers.updateBillingAddress(customer.getId(), newAddress);
        controlCustomers.salvare();
    }

    public void viewProfile(){
        ArrayList<Customers> details = controlCustomers.viewProfile(controlCustomers.getCustomer(customer.getId()).getId());
        String text = "";
        for (Customers customer : details) {
            text += "Name: " + customer.getFullName() + "\n";
            text += "Email: " + customer.getEmail() + "\n";
            text += "Password: " + customer.getPassword() + "\n";
            text += "Billing address: " + customer.getBillingAddress();
        }
        System.out.println(text);
    }


}
