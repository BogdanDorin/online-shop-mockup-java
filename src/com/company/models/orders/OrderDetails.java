package com.company.models.orders;

public class OrderDetails {
    private int id;
    private int orderID;
    private int productID;
    private int price;
    private int quantity;

    public OrderDetails(int id, int orderID, int productID, int price, int quantity) {
        this.id = id;
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetails(String text) {

        String[] word = text.split(",");
        id = Integer.parseInt(word[0]);
        orderID = Integer.parseInt(word[1]);
        productID = Integer.parseInt(word[2]);
        price = Integer.parseInt(word[3]);
        quantity = Integer.parseInt(word[4]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String descriptionOD() {
        String text = "id: " + id + "\n";
        text += "orderID: " + orderID + "\n";
        text += "productID: " + productID + "\n";
        text += "price: " + price + "\n";
        text += "quantity: " + quantity + "\n";
        return text;
    }

    @Override
    public String toString() {
        String text = id + ",";
        text += orderID + ",";
        text += productID + ",";
        text += price + ",";
        text += quantity + ",";
        return text;
    }

    @Override
    public boolean equals(Object obj) {

        OrderDetails orderDetails = (OrderDetails) obj;
        return this.id == id && this.orderID == orderID;
    }

}
