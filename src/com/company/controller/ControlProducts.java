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

    public void stergereProdus(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id)
                products.remove(id);
        }
    }

    public void adaugareProdus(Products product1) {

        products.add(product1);
    }

    public Products getProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return products.get(i);
            }
        }
        return null;
    }

    public Products getProductname(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                return products.get(i);
            }
        }
        return null;
    }

    public void updateStock(int id, int stock) {
        Products products1 = getProduct(id);
        products1.setStock(stock);
    }

    public void afisare() {
        for (Products product : products) {
            System.out.println(product.description());
        }
    }

    @Override
    public String toString() {
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

            p.print(this.toString());

            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int nextId() {
        if (products.size() == 0) {
            return 1;
        } else {
            return products.get(products.size() - 1).getId() + 1;
        }
    }

}
