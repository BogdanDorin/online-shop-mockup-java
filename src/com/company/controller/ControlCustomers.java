package com.company.controller;

import com.company.models.orders.Customers;
import com.company.models.orders.OrderDetails;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlCustomers {
    private ArrayList<Customers> controlCustomers;
    private String path;

    public ControlCustomers(String path) {
        controlCustomers = new ArrayList<>();
        this.path = path;
        load();
    }

    public void load() {
        controlCustomers.clear();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                if (text != "")
                    controlCustomers.add(new Customers(text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customers getCustomer(int id) {
        for (int i = 0; i < controlCustomers.size(); i++) {
            if (controlCustomers.get(i).getId() == id) {
                return controlCustomers.get(i);
            }
        }
        return null;
    }

    public Customers getCustomerEmailPass(String email, String password) {
        for (int i = 0; i < controlCustomers.size(); i++) {
            if (controlCustomers.get(i).getEmail().equals(email) && controlCustomers.get(i).getPassword().equals(password))
                return controlCustomers.get(i);
        }
        return null;
    }

    public void addCustomer(Customers customer) {
        controlCustomers.add(customer);
    }

    public void deleteCustomer(int id) {
        for (int i = 0; i < controlCustomers.size(); i++) {
            if (controlCustomers.get(i).getId() == id) {
                controlCustomers.remove(id);
            }
        }
    }

    public void updatePassword(int id, String newPassword) {
        Customers customer = getCustomer(id);
        customer.setPassword(newPassword);
    }

    public void updateName(int id, String newName) {
        Customers customer = getCustomer(id);
        customer.setFullName(newName);
    }

    public void updateEmail(int id, String newEmail) {
        Customers customer = getCustomer(id);
        customer.setEmail(newEmail);
    }

    public void updateBillingAddress(int id, String newBillingAddress) {
        Customers customer = getCustomer(id);
        customer.setBillingAddress(newBillingAddress);
    }

    @Override
    public String toString() {
        String text = "";
        for (Customers customers : controlCustomers) {
            text += customers.toString();
            text += "\n";
        }
        return text;
    }

    public void salvare() {
        try {
            File file = new File(path);
            FileWriter w = new FileWriter(file);
            PrintWriter p = new PrintWriter(w);
            p.print(this.toString());
            p.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int nextId() {
        if (controlCustomers.size() == 0) {
            return 1;
        } else {
            return controlCustomers.get(controlCustomers.size() - 1).getId() + 1;
        }
    }

    public ArrayList<Customers> viewProfile(int customerID) {
        ArrayList<Customers> list = new ArrayList<>();
        for (Customers customer : controlCustomers) {
            if (customer.getId() == customerID) {
                list.add(customer);
            }
        }
        return list;
    }
}
