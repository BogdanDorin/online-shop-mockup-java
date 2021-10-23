package com.company.models.orders;

public class Orders {
    private int id;
    private int customerID;
    private int amount;
    private String shippingAddress;
    private String orderAddress;

    public Orders(int id, int customerID, int amount, String shippingAddress, String orderAddress) {
        this.id = id;
        this.customerID = customerID;
        this.amount = amount;
        this.shippingAddress = shippingAddress;
        this.orderAddress = orderAddress;
    }

    public Orders(String text) {

        String[] word = text.split(",");
        id = Integer.parseInt(word[0]);
        customerID = Integer.parseInt(word[1]);
        amount = Integer.parseInt(word[2]);
        shippingAddress = word[3];
        orderAddress = word[4];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String descriptionO() {
        String text = "id: " + id + "\n";
        text += "customer's id: " + customerID + "\n";
        text += "ammount: " + amount + "\n";
        text += "shipping address: " + shippingAddress + "\n";
        text += "order address: " + orderAddress + "\n";
        return text;
    }


    @Override
    public String toString() {
        String text = id + ",";
        text += customerID + ",";
        text += amount + ",";
        text += shippingAddress + ",";
        text += orderAddress + ",";
        return text;
    }

    @Override
    public boolean equals(Object obj) {

        Orders orders = (Orders) obj;
        return this.id == id && this.customerID == customerID;

    }


}
