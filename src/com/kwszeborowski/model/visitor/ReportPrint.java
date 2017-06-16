package com.kwszeborowski.model.visitor;

import com.kwszeborowski.model.ReportToConsole;
import com.kwszeborowski.model.ReportToFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ReportPrint implements Visitor {

    public void print(ReportToConsole p) {
        p.printReport();
    }

    public void print(ReportToFile p) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        p.printReport();
    }
}
