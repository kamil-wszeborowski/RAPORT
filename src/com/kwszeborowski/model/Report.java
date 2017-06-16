package com.kwszeborowski.model;

import com.kwszeborowski.model.visitor.Visitor;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Report {

    private List<Client> clients;
    private List<Order> orders;
    private List<Product> products;

    public Report(List<Client> clients, List<Order> orders, List<Product> products) {
        this.clients = clients;
        this.orders = orders;
        this.products = products;
    }

    public Report() {}

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public abstract void accept(Visitor v) throws FileNotFoundException;

    public void printReport() {
        List<Client> clients = this.clients;
        List<Order> orders = this.orders;
        List<Product> products = this.products;
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



