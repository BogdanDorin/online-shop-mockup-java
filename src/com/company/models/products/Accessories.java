package com.company.models.products;

public class Accessories extends Products {
    private String use;
    private String color;

    public Accessories(int id, String name, int price, int stock, String use, String color) {
        super(id, name, price, "Accesories", stock);
        this.use = use;
        this.color = color;
    }

    public Accessories(String text) {
        super(text);
        String[] word = text.split(",");
        use = word[5];
        color = word[6];
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String description(){
        String text = "ID: " + getId()+ "\n";
        text += "Product's name: " + getName() + "\n";
        text += "Price: " + getPrice() + "\n";
        text += "Category: " + getCategory() + "\n";
        text += "Stock: " + getStock() + "\n";
        text += "Product's use: " + use + "\n";
        text += "Color: " + color + "\n";
        return text;
    }

    @Override
    public String toString() {
        String text = getId() + ",";
        text += getName() + ",";
        text += getPrice() + ",";
        text += getCategory() + ",";
        text += getStock() + ",";
        text += use + ",";
        text += color + ",";
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        Accessories accessories = (Accessories) obj;
        return this.use.equals(use) && this.color.equals(color);
    }
}

