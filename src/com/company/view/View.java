package com.company.view;

import com.company.controller.ControlCustomers;
import com.company.models.orders.Customers;

import java.nio.file.Path;
import java.util.Scanner;

public class View {
    private ControlCustomers controlCustomers;

    private Scanner scanner;

    public View(){
        controlCustomers = new ControlCustomers(Path.of("src", "com", "company", "resources", "customers.txt").toString());
        scanner = new Scanner(System.in);
    }
    public void loginMenu() {
        System.out.println("\n=================== Login Menu ===================");
        System.out.println("Use 1 to login");
        System.out.println("==================================================");
    }

    public void account() {
        boolean ok = true;
        loginMenu();
        while (ok == true) {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    ok = false;
                    break;
                case 1:
                    login();
                    break;
                default:
                    loginMenu();
                    break;
            }
        }
    }

    public void login() {
        System.out.println("Enter an email:");
        String email = scanner.nextLine();

        if (controlCustomers.getCustomerEmail(email) == null) {
            System.out.println("It looks like you don't have an account with this email. Creat an account.");
            System.out.println("Type the password:");
            String password = scanner.nextLine();
            System.out.println("Enter the full name:");
            String name = scanner.nextLine();
            System.out.println("Enter the billing address:");
            String bAdress = scanner.nextLine();

            Customers newC = new Customers(
                    controlCustomers.nextId(),
                    email,
                    password,
                    name,
                    bAdress
            );
            controlCustomers.addCustomer(newC);
            controlCustomers.salvare();


            ViewUser viewUser = new ViewUser(newC);
            viewUser.play();
        } else {
            System.out.println("Enter the password:");
            String password1 = scanner.nextLine();

            Customers is = controlCustomers.getCustomerEmailPass(email, password1);

            if (is != null) {

                ViewUser viewUser = new ViewUser(is);
                viewUser.play();
            }
        }
    }
}
