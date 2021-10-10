package com.company.controller;

import com.company.models.products.Accessories;
import com.company.models.products.Computers;
import com.company.models.products.Periferice;
import com.company.models.products.Products;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlProducts {
    private ArrayList<Products> products;
    private String path;

    public ControlProducts(String path) {
        products = new ArrayList<>();
        this.path = path;
        load();
    }

    public void load() {

        products.clear();
        try {

            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String text = scanner.nextLine();

                if (text.contains("Accessories")) {
                    this.products.add(new Accessories(text));
                } else if (text.contains("Computers")) {
                    this.products.add(new Computers(text));
                } else if (text.contains("Periferice")) {
                    this.products.add(new Periferice(text));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stergereProdus(Products products1) {
        products.remove(products1);
    }

    public void adaugareProdus(Products products1) {
        products.add(products1);
    }

    public Products getProduct(String name) {
        for (Products product : products)
            if (product.getName().equals(name))
                return product;
        return null;
    }

    public void updateStock(String name, int stock) {
        Products products1 = getProduct(name);
        products1.setStock(stock);
    }

    public String save() {
        String text = "";
        for (Products products1 : products) {
            text += products1.toString();
            text += "\n";
        }
        return text;
    }

    public void salvare() {
        try {
            File file = new File(path);
            FileWriter w = new FileWriter(file);
            PrintWriter p = new PrintWriter(w);

            p.print(this.save());

            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
