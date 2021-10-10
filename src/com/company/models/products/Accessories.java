package com.company.models.products;

public class Accessories extends Products {
    private String use;
    private String culoare;

    public Accessories(int id, String name, int price, int stock, String use, String culoare) {
        super(id, name, price, "Accesories", stock);
        this.use = use;
        this.culoare = culoare;
    }

    public Accessories(String text) {
        super(text);
        String[] word = text.split(",");
        use = word[5];
        culoare = word[6];
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    @Override
    public String toString() {

        String text = getId() + ",";
        text += getName() + ",";
        text += getPrice() + ",";
        text += getCategory() + ",";
        text += getStock() + ",";
        text += use + ",";
        text += culoare + ",";
        return text;
    }

    @Override
    public boolean equals(Object obj) {

        Accessories accessories = (Accessories) obj;
        return this.use.equals(use) && this.culoare.equals(culoare);
    }


}

