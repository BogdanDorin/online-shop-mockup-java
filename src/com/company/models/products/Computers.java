package com.company.models.products;

public class Computers extends Products {
    private String marca;
    private String tip;

    public Computers(int id, String name, int price, int stock, String marca, String tip) {
        super(id, name, price, "Computers", stock);
        this.marca = marca;
        this.tip = tip;
    }

    public Computers(String text) {
        super(text);
        String[] word = text.split(",");
        marca = word[5];
        tip = word[6];
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        String text = getId() + ",";
        text += getName() + ",";
        text += getPrice() + ",";
        text += getCategory() + ",";
        text += getStock() + ",";
        text += marca + ",";
        text += tip + ",";

        return text;
    }

    @Override
    public boolean equals(Object obj) {

        Computers computers = (Computers) obj;
        return this.marca.equals(marca) && this.tip.equals(tip);
    }
}
