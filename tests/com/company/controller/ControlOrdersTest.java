package com.company.controller;

import com.company.models.orders.Orders;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ControlOrdersTest {

    @Test
    public void testAddOrder() {
        ControlOrders controlOrders = new ControlOrders(Path.of("src", "com", "company", "resources", "orders.txt").toString());

        controlOrders.addOrder(new Orders(5, 99, 123, "sibiu", "sibiu"));

        controlOrders.salvare();

        assertEquals(true, controlOrders.getOrder(5) != null);
    }

    @Test
    public void testAddOrder2() {
        ControlOrders controlOrders = new ControlOrders(Path.of("src", "com", "company", "resources", "orders.txt").toString());

        for (int i = 0; i < 10; i++) {
            controlOrders.addOrder(new Orders(6 + i, 1000, 1000, "test", "test"));

        }
        controlOrders.salvare();

        for (int i = 0; i < 10; i++) {
            assertEquals(true, controlOrders.getOrder(6 + i) != null);
        }
    }

    @Test
    public void testUpdateAmmount(){
        ControlOrders controlOrders = new ControlOrders(Path.of("src", "com", "company", "resources", "orders.txt").toString());

        controlOrders.updateAmmount(5, 1000);
        controlOrders.salvare();

        assertEquals(true, controlOrders.getOrder(5).getAmmount() == 1000);
    }

    @Test
    public void testUpdateShippingAddress(){
        ControlOrders controlOrders = new ControlOrders(Path.of("src", "com", "company", "resources", "orders.txt").toString());

        controlOrders.updateShippingAddress(5,"new shipping address");
        controlOrders.salvare();

        assertEquals(true, controlOrders.getOrder(5).getShippingAddress() == "new shipping address");
    }

    @Test
    public void testDeleteOrder(){
        ControlOrders controlOrders = new ControlOrders(Path.of("src", "com", "company", "resources", "orders.txt").toString());

        controlOrders.deleteOrder(5);
        controlOrders.salvare();

        assertEquals(null, controlOrders.getOrder(5));
    }
}