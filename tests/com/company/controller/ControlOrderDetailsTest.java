package com.company.controller;

import com.company.models.orders.OrderDetails;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ControlOrderDetailsTest {
    @Test
    public void testAddOD() {
        ControlOrderDetails controlOrderDetails = new ControlOrderDetails(Path.of("src", "com", "company", "resources", "orderdetails.txt").toString());

        controlOrderDetails.addOrderDetails(new OrderDetails(799, 999, 999, 546, 77));
        controlOrderDetails.salvare();
        assertEquals(true, controlOrderDetails.getOrderDetails(799) != null);
    }

//    @Test
//    public void testDeleteOD(){
//        ControlOrderDetails controlOrderDetails = new ControlOrderDetails(Path.of("src","com","company","resources","orderdetails.txt").toString());
//
//        controlOrderDetails.deleteOrderDetails(1);
//        controlOrderDetails.salvare();
//        assertEquals(true, controlOrderDetails.getOrderDetails(1) == null);
//
//    }

    @Test
    public void testUpdateQuantity() {
        ControlOrderDetails controlOrderDetails = new ControlOrderDetails(Path.of("src", "com", "company", "resources", "orderdetails.txt").toString());

        controlOrderDetails.updateODquantity(7, 100);
        controlOrderDetails.salvare();

        assertEquals(true, controlOrderDetails.getOrderDetails(7).getQuantity() == 100);
    }

    @Test
    public void testUpdatePrice() {
        ControlOrderDetails controlOrderDetails = new ControlOrderDetails(Path.of("src", "com", "company", "resources", "orderdetails.txt").toString());

        controlOrderDetails.updateODprice(7, 100);
        controlOrderDetails.salvare();

        assertEquals(true, controlOrderDetails.getOrderDetails(7).getPrice() == 100);

    }

}