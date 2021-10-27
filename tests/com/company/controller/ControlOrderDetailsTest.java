package com.company.controller;

import com.company.models.orders.OrderDetails;
import com.company.models.products.Products;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControlOrderDetailsTest {
    @Test
    public void testAddOD() {
        ControlOrderDetails controlOrderDetails = new ControlOrderDetails(Path.of("src", "com", "company", "resources", "orderdetails.txt").toString());

        controlOrderDetails.addOrderDetails(new OrderDetails(5, 999, 999, 546, 77));
        controlOrderDetails.salvare();
        assertEquals(true, controlOrderDetails.getOrderDetails(5) != null);
    }

    @Test
    public void testDeleteOD(){
        ControlOrderDetails controlOrderDetails = new ControlOrderDetails(Path.of("src","com","company","resources","orderdetails.txt").toString());

        controlOrderDetails.deleteOrderDetails(5);
        controlOrderDetails.salvare();
        assertEquals(false, controlOrderDetails.getOrderDetails(6) != null);

    }

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


    @Test
    public void testViewOrder() {
        ControlProducts controlProducts = new ControlProducts(Path.of("src", "com", "company", "resources", "products.txt").toString());
        ControlOrderDetails controlOrderDetails = new ControlOrderDetails(Path.of("src", "com", "company", "resources", "orderdetails.txt").toString());
        ArrayList<OrderDetails> detalii = controlOrderDetails.viewOrder(1);

        String text = "In cos avem \n";

        for (OrderDetails o : detalii) {

            Products p = controlProducts.getProduct(o.getProductID());

            text += p.getName();
            text += "\n" + o.getQuantity() + " bucati";
            text += "\n" + "in valaore de " + o.getPrice();

        }

        System.out.println(text);
    }

}