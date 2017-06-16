package com.kwszeborowski.model.visitor;

import com.kwszeborowski.model.ReportToConsole;
import com.kwszeborowski.model.ReportToFile;

import java.io.FileNotFoundException;

public interface Visitor {
    public void print(ReportToConsole p);
    public void print(ReportToFile p) throws FileNotFoundException;
}

