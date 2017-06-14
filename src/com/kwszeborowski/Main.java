package com.kwszeborowski;


import com.kwszeborowski.model.Client;
import com.kwszeborowski.model.Order;
import com.kwszeborowski.model.Product;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        List<Client> list = new ArrayList<Client>();
        List<Order> list_of_orders = new ArrayList<Order>();

        String [] names = {"Kamil","Tomek","Marcin","Andrzej","Tomek"};
        String [] cities = {"Gdynia","Sopot","Gdańsk","Olsztyn","Śląsk"};
        int [] ages = {22,31,21,22,13};
        String [] groups = {"Strengers","Butterfiles","True friends","Strengers","Strengers"};

        int number_of_objects = 10;
        for(int i=0; i<number_of_objects; i++){

            Random generator = new Random();
            String name = names[generator.nextInt(5)];
            String city = cities[generator.nextInt(5)];
            int age = ages[generator.nextInt(5)];
            String group = groups[generator.nextInt(5)];
            list.add(new Client(i, name, city, age, group));
        }

        for (int i=0; i<list.size(); i++) {
            Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
            Product product = new Product(i, "name", 5);
            Order order = new Order(i, date, product, 5, list.get(i));
            list_of_orders.add(order);
            list.get(i).addOrder(order);
        }

        System.out.print(list);
        System.out.print(list_of_orders);
    }
}
