package com.company.controller;


import com.company.models.orders.Orders;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlOrders {
    private ArrayList<Orders> controlOrders;
    private String path;

    public ControlOrders(String path) {
        controlOrders = new ArrayList<>();
        this.path = path;
        load();
    }

    public void load() {
        controlOrders.clear();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                if (text != "")
                    controlOrders.add(new Orders(text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Orders getOrder(int id) {
        for (int i = 0; i < controlOrders.size(); i++)
            if (controlOrders.get(i).getId() == id)
                return controlOrders.get(i);
        return null;
    }

    public void addOrder(Orders order) {
        controlOrders.add(order);
    }

    public void deleteOrder(int id) {
        for (int i = 0; i < controlOrders.size(); i++) {
            if (controlOrders.get(i).getId() == id)
                controlOrders.remove(id);
        }
    }

    public void updateAmount(int id, int newAmount) {
        Orders order = getOrder(id);
        order.setAmount(newAmount);
    }

    public void updateShippingAddress(int id, String newShippingAddress) {
        Orders order = getOrder(id);
        order.setShippingAddress(newShippingAddress);
    }

    public void afisare() {
        for (Orders orders : controlOrders) {
            System.out.println(orders.descriptionO());
        }
    }

    @Override
    public String toString() {
        String text = "";
        for (Orders orders : controlOrders) {
            text += orders.toString();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int nextId() {
        if (controlOrders.size() == 0) {
            return 1;
        } else {
            return controlOrders.get(controlOrders.size() - 1).getId() + 1;
        }
    }


}
