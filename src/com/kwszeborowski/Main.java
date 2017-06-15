package com.kwszeborowski;


import com.kwszeborowski.model.Client;
import com.kwszeborowski.model.Order;
import com.kwszeborowski.model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        int userChoice;
        Scanner input = new Scanner(System.in);

        userChoice = menu();

        switch (userChoice) {
            case 1:
                PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
                System.setOut(out);
                generateReport();
                break;
            case 2:
                generateReport();
                break;
            case 4:
                break;
            default:
                generateReport();
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

    public static String generateString(int length) {
        Random generator = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i=0; i< length; i++){
            buf.append((char)(generator.nextInt(128-32)+32));
        }
        return buf.toString();

    }
    public static void generateReport() {

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

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%4s %10s %15s %5s %5s", "CLIENT ID", "NAME", "CITY", "AGE", "GROUP");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for(Client client: clients){
            System.out.format("%4s %15s %15s %5d %5s",
                    client.getId(), client.getName(), client.getCity(), client.getAge(), client.getGroup());
            System.out.println();
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%4s %10s %12s %8s %12s %12s %15s %12s", "ORDER ID", "DATE", "PRODUCT ID", "MARGIN", "CLIENT ID", "CLIENT NAME", "CLIENT CITY", "GROUP");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

        int margin_sum = 0;
        Map<String, Long> occurrences =
                orders.stream().collect(Collectors.groupingBy(w -> w.getClient().getCity(), Collectors.counting()));

        Map<String, Long> most_products =
                orders.stream().collect(Collectors.groupingBy(w -> w.getProduct().getName(), Collectors.counting()));


        Calendar c = Calendar.getInstance();

        Map<String, Long> most_group =
                orders.stream()
                        .filter(w -> w.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == c.get(Calendar.MONTH) +1)
                        .collect(Collectors.groupingBy(w -> w.getClient().getGroup(), Collectors.counting()));

        for(Order order: orders){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            String date = sdf.format(order.getDate());
            Client client = order.getClient();
            System.out.format("%4s %15s %7d %10d %8s %15s %15s %15s",
                    order.getId(), date, order.getProduct().getId(), order.getMargin(), client.getId(), client.getName(), client.getCity(), client.getGroup());
            System.out.println();
            margin_sum += order.getMargin();
        }

        String most_popular_city = Collections.max(occurrences.entrySet(), Map.Entry.comparingByValue()).getKey();
        String most_selled_product = Collections.max(most_products.entrySet(), Map.Entry.comparingByValue()).getKey();
        String less_selled_product = Collections.min(most_products.entrySet(), Map.Entry.comparingByValue()).getKey();
        String most_active_group = Collections.max(most_group.entrySet(), Map.Entry.comparingByValue()).getKey();

        System.out.println();
        System.out.println("Ilość klientów: " + clients.size());
        System.out.println("Ilość zamówień: " + orders.size());
        System.out.println("Wpływ ze sprzedaży: " + margin_sum);
        System.out.println("Najpopularniejsze miasto wysyłki: " + most_popular_city);
        System.out.println("Najczęściej sprzedawany produkt: " + most_selled_product);
        System.out.println("Najrzdziej sprzedawany produkt: " + less_selled_product);
        System.out.println("Najbardziej aktywna grupa w ostatnim miesiącu: " + most_active_group);
    }

}
