package com.company.models.products;

public class Products {
    private int id;
    private String name;
    private int price;
    private String category;
    private int stock;

    public Products(int id, String name, int price, String category, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    public Products(String text){

        String[] word = text.split(",");
        id = Integer.parseInt(word[0]);
        name = word[1];
        price = Integer.parseInt(word[2]);
        category = word[3];
        stock = Integer.parseInt(word[4]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String description(){
        String text = "ID: " + id + "\n";
        text += "Product's name: " + name + "\n";
        text += "Price: " + price + "\n";
        text += "Category: " + category + "\n";
        text += "Stock: " + stock + "\n";
        return text;
    }

    @Override
    public String toString(){
        String text = id + ",";
        text += name + ",";
        text += price + ",";
        text += category + ",";
        text += stock + ",";
        return text;
    }


    @Override
    public boolean equals(Object obj) {

        Products products = (Products) obj;
        return this.name.equals(name) && this.id == id;
    }
}
