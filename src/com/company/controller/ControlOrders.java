package com.company.controller;

import com.company.models.orders.OrderDetails;
import com.company.models.orders.Orders;
import com.company.models.products.Accessories;
import com.company.models.products.Computers;
import com.company.models.products.Periferice;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlOrders {
    private ArrayList<Orders> controlOrders;
    private String path;

    public ControlOrders(String path){
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

    public Orders getOrder(int id){
        for (int i = 0; i < controlOrders.size(); i++)
            if (controlOrders.get(i).getId() == id)
                return controlOrders.get(i);
        return null;
    }

    public void addOrder(Orders order){ controlOrders.add(order); }

    public void deleteOrder(int id){
        for (Orders order : controlOrders)
            if (order.getId() == id)
                controlOrders.remove(id);

    }

    public void updateAmmount(int id, int newAmmount){
        Orders order = getOrder(id);
        order.setAmmount(newAmmount);
    }

    public void updateShippingAddress(int id, String newShippingAddress){
        Orders order = getOrder(id);
        order.setShippingAddress(newShippingAddress);
    }

    @Override
    public String toString(){
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
}
