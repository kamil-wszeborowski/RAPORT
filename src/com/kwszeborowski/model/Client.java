package com.kwszeborowski.model;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private String city;
    private int age;
    private String group;
    private Collection<Order> orders;


    public Client(int id, String name, String city, int age, String group) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
        this.group = group;
        this.orders = new ArrayList<Order>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

}
