package com.company.controller;

import com.company.models.orders.OrderDetails;
import com.company.models.orders.Orders;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlOrderDetails {
    private ArrayList<OrderDetails> orderDetailss1;
    private String path;

    public ControlOrderDetails(String path) {
        orderDetailss1 = new ArrayList<>();
        this.path = path;
        load();
    }

    public void load() {
        orderDetailss1.clear();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                if (text != "")
                    orderDetailss1.add(new OrderDetails(text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderDetails getOrderDetails(int id) {
        for (int i = 0; i < orderDetailss1.size(); i++) {
            if (orderDetailss1.get(i).getId() == id) {
                return orderDetailss1.get(i);
            }
        }
        return null;
    }

    public void updateODprice(int id, int newPrice) {
        OrderDetails orderDetails = getOrderDetails(id);
        orderDetails.setPrice(newPrice);
    }

    public void updateODquantity(int id, int newQuantity) {
        OrderDetails orderDetails = getOrderDetails(id);
        orderDetails.setQuantity(newQuantity);
    }

    public void deleteOrderDetails(int id) {
        for (int i = 0; i < orderDetailss1.size(); i++) {
            if (orderDetailss1.get(i).getId() == id)
                orderDetailss1.remove(id);
        }
    }

    public void addOrderDetails(OrderDetails orderDetails) {
        orderDetailss1.add(orderDetails);
    }

    @Override
    public String toString() {
        String text = "";
        for (OrderDetails orderDetails : orderDetailss1) {
            text += orderDetails.toString();
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
        if (orderDetailss1.size() == 0) {
            return 1;
        } else {
            return orderDetailss1.get(orderDetailss1.size() - 1).getId() + 1;
        }
    }

    public ArrayList<OrderDetails> viewOrder(int orderID){
        ArrayList<OrderDetails> list = new ArrayList<>();

       for (OrderDetails orderDetails: orderDetailss1){
           if (orderDetails.getOrderID() == orderID){
               list.add(orderDetails);
           }
       }
       return list;
    }

    public int orderAmount(int orderID){
        int contor = 0;
       for (int i = 0 ;i < orderDetailss1.size();i++){
           if (orderDetailss1.get(i).getOrderID() == orderID){
               contor++;
           }
       }
       return contor;
    }
}
