package com.company.controller;

import com.company.models.products.Computers;
import com.company.models.products.Periferice;
import com.company.models.products.Products;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControlProductsTest {

    @Test
    public void testAdaugareProdus() {
        ControlProducts controlProducts = new ControlProducts(Path.of("src", "com", "company", "resources", "products.txt").toString());

        controlProducts.adaugareProdus(new Computers(5, "testprodus", 999, 10, "test", "computers"));


        controlProducts.salvare();

        assertEquals(true, controlProducts.getProduct(5) != null);

    }

    @Test
    public void testStergereProdus() {
        ControlProducts controlProducts = new ControlProducts(Path.of("src", "com", "company", "resources", "products.txt").toString());

        controlProducts.stergereProdus(5);

        controlProducts.salvare();

        assertEquals(true, controlProducts.getProduct(5) == null);
    }

    @Test
    public void testUpdateStock() {
        ControlProducts controlProducts = new ControlProducts(Path.of("src", "com", "company", "resources", "products.txt").toString());

        controlProducts.updateStock(5, 1000);

        controlProducts.salvare();

        assertEquals(true, controlProducts.getProduct(5).getStock() == 1000);
    }


}