package com.company.models.products;

public class Computers extends Products {
    private String brand;
    private String type;

    public Computers(int id, String name, int price, int stock, String brand, String type) {
        super(id, name, price, "Computers", stock);
        this.brand = brand;
        this.type = type;
    }

    public Computers(String text) {
        super(text);
        String[] word = text.split(",");
        brand = word[5];
        type = word[6];
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String description(){
        String text = "ID: " + getId()+ "\n";
        text += "Product's name: " + getName() + "\n";
        text += "Price: " + getPrice() + "\n";
        text += "Category: " + getCategory() + "\n";
        text += "Stock: " + getStock() + "\n";
        text += "Brand: " + brand + "\n";
        text += "Type: " + type + "\n";
        return text;
    }

    @Override
    public String toString() {
        String text = getId() + ",";
        text += getName() + ",";
        text += getPrice() + ",";
        text += getCategory() + ",";
        text += getStock() + ",";
        text += brand + ",";
        text += type + ",";

        return text;
    }

    @Override
    public boolean equals(Object obj) {
        Computers computers = (Computers) obj;
        return this.brand.equals(brand) && this.type.equals(type);
    }
}
