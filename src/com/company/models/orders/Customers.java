package com.company.models.orders;

public class Customers {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private String billingAddress;

    public Customers(int id, String email, String password, String fullName, String billingAddress) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.billingAddress = billingAddress;
    }

    public Customers(String text) {

        String[] word = text.split(",");
        id = Integer.parseInt(word[0]);
        email = word[1];
        password = word[2];
        fullName = word[3];
        billingAddress = word[4];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String toSave() {

        String text = id + ",";
        text += email + ",";
        text += password + ",";
        text += fullName + ",";
        text += billingAddress + ",";
        return text;
    }


    @Override
    public String toString() {

        String text = "id: " + id + "\n";
        text += "email: " + email + "\n";
        text += "password: " + password + "\n";
        text += "full name: " + fullName + "\n";
        text += "billing address: " + billingAddress + "\n";
        return text;
    }

    @Override
    public boolean equals(Object obj) {

        Customers customers = (Customers) obj;
        return this.email.equals(email) && this.password.equals(password);
    }

}
