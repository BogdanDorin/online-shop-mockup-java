package com.company.controller;

import com.company.models.products.Periferice;
import com.company.models.products.Products;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControlProductsTest {

    @Test
    public void testStergereProdus(){
        ControlProducts controlProducts = new ControlProducts(Path.of("src","com","company","resources","products.txt").toString());
        controlProducts.stergereProdus(new Products(13,"Tastatura",325,"Periferice",6));
        controlProducts.salvare();
        assertEquals(false, controlProducts.getProduct(13) == null);
    }


}