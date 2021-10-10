package com.company.models.orders;

public class Orders {
    private int id;
    private int customerID;
    private int ammount;
    private String shippingAddress;
    private String orderAddress;

    public Orders(int id, int customerID, int ammount, String shippingAddress, String orderAddress) {
        this.id = id;
        this.customerID = customerID;
        this.ammount = ammount;
        this.shippingAddress = shippingAddress;
        this.orderAddress = orderAddress;
    }

    public Orders(String text) {

        String[] word = text.split(",");
        id = Integer.parseInt(word[0]);
        customerID = Integer.parseInt(word[1]);
        ammount = Integer.parseInt(word[2]);
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

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
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

    public String toSave() {

        String text = id + ",";
        text += customerID + ",";
        text += ammount + ",";
        text += shippingAddress + ",";
        text += orderAddress + ",";
        return text;
    }


    @Override
    public String toString() {

        String text = "id: " + id + "\n";
        text += "customer's id: " + customerID + "\n";
        text += "ammount: " + ammount + "\n";
        text += "shipping address: " + shippingAddress + "\n";
        text += "order address: " + orderAddress + "\n";
        return text;
    }

    @Override
    public boolean equals(Object obj){

        Orders orders = (Orders) obj;
        return this.id == id && this.customerID == customerID;

    }


}
