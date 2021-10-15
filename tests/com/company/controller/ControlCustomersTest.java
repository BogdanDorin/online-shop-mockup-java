package com.company.controller;

import com.company.models.orders.Customers;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ControlCustomersTest {
    @Test
    public void testAddCustomer(){
        ControlCustomers controlCustomers = new ControlCustomers(Path.of("src","com","company","resources","customers.txt").toString());

        controlCustomers.addCustomer(new Customers(4,"test@gmail.com","test123","test t","greece"));
        controlCustomers.salvare();

        assertEquals(true, controlCustomers.getCustomer(4).getEmail() == "test@gmail.com");

    }

    @Test
    public void testDeleteCustomer(){
        ControlCustomers controlCustomers = new ControlCustomers(Path.of("src","com","company","resources","customers.txt").toString());

        controlCustomers.deleteCustomer(4);
        controlCustomers.salvare();

        assertEquals(true, controlCustomers.getCustomer(4) == null);
    }

    @Test
    public void testUpdatePassword(){
        ControlCustomers controlCustomers = new ControlCustomers(Path.of("src","com","company","resources","customers.txt").toString());

        controlCustomers.updatePassword(4,"newTestPass");
        controlCustomers.salvare();

        assertEquals(true, controlCustomers.getCustomer(4).getPassword() == "newTestPass");
    }

    @Test
    public void testUpdateName(){
        ControlCustomers controlCustomers = new ControlCustomers(Path.of("src","com","company","resources","customers.txt").toString());

        controlCustomers.updateName(4, "newTestName");
        controlCustomers.salvare();

        assertEquals(true, controlCustomers.getCustomer(4).getFullName() == "newTestName");
    }
}