package com.kwszeborowski.model;

import com.kwszeborowski.model.visitor.Visitor;

import java.util.List;

public class ReportToConsole extends Report {

    public ReportToConsole(List<Client> clients, List<Order> orders, List<Product> products) {
        super(clients, orders, products);
    }

    public ReportToConsole() {
        super();
    }

    public void accept(Visitor v) {
        v.print(this);
    }
}
