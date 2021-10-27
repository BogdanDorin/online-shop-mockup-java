package com.company;

import com.company.controller.ControlOrders;
import com.company.models.orders.Customers;
import com.company.models.orders.Orders;
import com.company.view.View;
import com.company.view.ViewUser;


import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
//        Customers customers = new Customers(5,"test@gmail.com", "newTestPass","newTestName","greece");
//        ViewUser viewUser = new ViewUser(customers);
//
//        viewUser.play();


        View view = new View();
        view.account();

    }
}
