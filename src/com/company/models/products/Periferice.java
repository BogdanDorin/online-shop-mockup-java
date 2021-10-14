package com.company.models.products;

public class Periferice extends Products {
    private String model;
    private String descriere;

    public Periferice(int id, String name, int price, int stock, String model, String descriere) {
        super(id, name, price, "periferice", stock);
        this.model = model;
        this.descriere = descriere;
    }

    public Periferice(String text) {
        super(text);
        String[] word = text.split(",");
        model = word[5];
        descriere = word[6];
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String description(){
        String text = "ID: " + getId()+ "\n";
        text += "Product's name: " + getName() + "\n";
        text += "Price: " + getPrice() + "\n";
        text += "Category: " + getCategory() + "\n";
        text += "Stock: " + getStock() + "\n";
        text += "Model: " + model + "\n";
        text += "Description: " + descriere + "\n";
        return text;
    }

    @Override
    public String toString() {

        String text = getId() + ",";
        text += getName() + ",";
        text += getPrice() + ",";
        text += getCategory() + ",";
        text += getStock() + ",";
        text += model + ",";
        text += descriere + ",";

        return text;
    }

    @Override
    public boolean equals(Object obj) {

        Periferice periferice = (Periferice) obj;
        return this.model.equals(model);
    }
}
