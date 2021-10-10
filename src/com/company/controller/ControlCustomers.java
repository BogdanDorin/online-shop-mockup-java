package com.company.controller;

import com.company.models.orders.Customers;
import com.company.models.products.Products;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlCustomers {
    private ArrayList<Customers> customers;
    private String path;

    public ControlCustomers(String path){
        customers = new ArrayList<>();
        this.path = path;
        load();
    }

    public void load(){
        customers.clear();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                String text = scanner.nextLine();
                if (text != "")
                    customers.add(new Customers(text));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adaugareCustomer(Customers customer){
        customers.add(customer);
    }

    public void stergereCustomer(Customers customer){
        customers.remove(customer);
    }

    public Customers getCustomer(int id){

        for (int i = 0; i < customers.size(); i++){
            if (customers.get(i).getId()==id){
                return customers.get(i);
            }
        }
        return null;
    }

    public void updatePassword(int id, String password){
        Customers customer = getCustomer(id);
        customer.setPassword(password);
    }

    public String save() {
        String text = "";
        for (Customers customer : customers) {
            text += customer.toSave();
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
