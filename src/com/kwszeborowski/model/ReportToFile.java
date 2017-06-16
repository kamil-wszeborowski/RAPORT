package com.kwszeborowski.model;

import com.kwszeborowski.model.visitor.Visitor;

import java.io.FileNotFoundException;
import java.util.List;

public class ReportToFile extends Report {

    public ReportToFile(List<Client> clients, List<Order> orders, List<Product> products) {
        super(clients, orders, products);
    }

    public ReportToFile() {
        super();
    }

    public void accept(Visitor v) throws FileNotFoundException {
        v.print(this);
    }

}
