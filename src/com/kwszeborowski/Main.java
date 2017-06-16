package com.kwszeborowski;


import com.kwszeborowski.model.*;
import com.kwszeborowski.model.visitor.ReportPrint;
import com.kwszeborowski.model.visitor.Visitor;

import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        int userChoice;
        Scanner input = new Scanner(System.in);

        userChoice = menu();
        Visitor v = new ReportPrint();

        switch (userChoice) {
            case 1:
                Report reportToFile = generateReport(new ReportToFile());
                reportToFile.accept(v);
                break;
            case 2:
                Report reportToConsole = generateReport(new ReportToConsole());
                reportToConsole.accept(v);
                break;
            case 3:
                break;
            default:
                break;
        }
    }


    public static int menu() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("-----------MENU----------");
        System.out.println("1 - Wydrukuj raport do pliku");
        System.out.println("2 - Wyświetl raport");
        System.out.println("3 - Wyjdź");

        selection = input.nextInt();
        return selection;
    }

    public static Report generateReport(Report report) {
        List<Client> clients = new ArrayList<Client>();
        List<Order> orders = new ArrayList<Order>();
        List<Product> products = new ArrayList<Product>();

        String [] names = {"Kamil","Tomek","Marcin","Andrzej", "Krzysztof", "Stefan"};
        String [] cities = {"Gdynia","Sopot","Gdańsk","Olsztyn","Śląsk"};
        String [] groups = {"Butterfiles","True friends","Strengers","Another Ones"};
        String [] product_names = {"aaaa","bbbbb","cccccc","ddddd","eeeee"};
        int [] ages = {22,31,21,22,13};
        int number_of_objects = 10;

        Random generator = new Random();

        for(int i=0; i < number_of_objects; i++){
            String name = names[generator.nextInt(names.length)];
            String city = cities[generator.nextInt(cities.length)];
            int age = ages[generator.nextInt(ages.length)];
            String group = groups[generator.nextInt(groups.length)];
            clients.add(new Client(i, name, city, age, group));

            products.add(new Product(i, product_names[generator.nextInt(5)],  generator.nextInt(20)));
        }

        for (int i=0, id=0; i < clients.size(); i++) {
            int n = generator.nextInt(24);
            int dateOffset = (24*60*60*1000) * n;
            Date date = new Date();
            date.setTime(date.getTime() - dateOffset);
            int number_of_client_orders = generator.nextInt(2) + 1;
            for (int x=0; x < number_of_client_orders; x++) {
                Order order = new Order(id, date, products.get( generator.nextInt(number_of_objects)), generator.nextInt(20), clients.get(i));
                orders.add(order);
                clients.get(i).addOrder(order);
                id += 1;
            }
        }
        report.setClients(clients);
        report.setOrders(orders);
        report.setProducts(products);
        return report;
    }



}
