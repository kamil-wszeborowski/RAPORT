package com.kwszeborowski.model;

import java.util.Date;

public class Order {
    private int id;
    private Date date;
    private Product product;
    private int margin;
    private Client client;

    public Order(int id, Date date, Product product, int margin, Client client) {
        this.id = id;
        this.date = date;
        this.product = product;
        this.margin = margin;
        this.client = client;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
