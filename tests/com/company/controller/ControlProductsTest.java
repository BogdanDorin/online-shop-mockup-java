package com.company.controller;

import com.company.models.products.Computers;
import com.company.models.products.Periferice;
import com.company.models.products.Products;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControlProductsTest {

    @Test
    public void testStergereProdus(){
        ControlProducts controlProducts = new ControlProducts(Path.of("src","com","company","resources","products.txt").toString());

        controlProducts.stergereProdus(3);

        controlProducts.salvare();
        assertEquals(true, controlProducts.getProduct(13) == null);
    }

    @Test
    public void testAdaugareProdus(){
        ControlProducts controlProducts = new ControlProducts(Path.of("src","com","company","resources","products.txt").toString());

        controlProducts.adaugareProdus(new Computers(123,"testprodus",999,10,"test","computers"));


        controlProducts.salvare();

        assertEquals(true, controlProducts.getProduct(123) != null);

    }

    @Test
    public void testUpdateStock(){
        ControlProducts controlProducts = new ControlProducts(Path.of("src","com","company","resources","products.txt").toString());

        controlProducts.updateStock(123,1000);

        controlProducts.salvare();

        assertEquals(true, controlProducts.getProduct(123).getStock() == 1000);
    }


}