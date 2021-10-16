package com.company;

import com.company.controller.ControlOrders;
import com.company.models.orders.Customers;
import com.company.models.orders.Orders;
import com.company.view.ViewUser;

import javax.swing.text.View;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        Customers customers = new Customers(0, "dorinb@gmail.com", "dorin123", "Dorin Bogdan", "Sibiu");
        ViewUser viewUser = new ViewUser(customers);

        viewUser.play();

    }
}
