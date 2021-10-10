package com.company.controller;

import com.company.models.orders.OrderDetails;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlOrderDetails {
    private ArrayList<OrderDetails> orderDetails;
    private String path;

    public ControlOrderDetails(String path) {
        orderDetails = new ArrayList<>();
        this.path = path;
        load();
    }

    public void load() {
        orderDetails.clear();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                if (text != "")
                    orderDetails.add(new OrderDetails(text));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderDetails getOrderDetails(int id){
        for (int i = 0; i < orderDetails.size(); i++){
            if (orderDetails.get(i).getId() == id){
                return orderDetails.get(i);
            }
        }
        return null;
    }



}
