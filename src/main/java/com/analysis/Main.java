package com.analysis;

import com.aspose.cells.Workbook;

import java.io.File;
import java.util.Scanner;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // eventually implement with keyword arguments
        // private repo to throw auth exception

        // take input from user using Scanner
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your url: ");
        String url = sc.next();
        String outputType = "";
        while (!outputType.equals("c") && !outputType.equals("x")) {
            System.out.println("Please enter a (c) for csv or (x) for excel: ");
            outputType = sc.next();
        }
        System.out.print("What would you like the output file to be called: ");
        String filename = sc.next();
        // closed scanner
        sc.close();

        // instantiate Fetch object with user URL
        Fetch fetch = new Fetch(url);
        // clone repository to local using Fetch class
        fetch.cloneRepository();

        // get filepath from Fetch class
        File f = fetch.getOutFile();

        // run PMD using Analyzer class, which picks out java
        // files and analyzes them for code style
        Analyzer analyzer = new Analyzer(f, filename);
        analyzer.runPMD();

        if (outputType.equals("x")) {
            // save to excel
            CSVToExcel converter = new CSVToExcel(filename);
            Workbook wb = converter.csvToExcel(filename + ".csv");
            // delete csv file
            try {
                Files.deleteIfExists(Paths.get("./" + filename + ".csv"));
                System.out.println("Successful delete");
            } catch (NoSuchFileException e) {
                System.out.println("No file exists");
            }
        }


        // maybe use JGit instead to show new directory created or contents
        if (outputType.equals("c")) {
            System.out.format("Done! Check the %s.csv file for output", filename);
        } else {
            System.out.format("Done! Check the %s.xlsx file for output", filename);
        }
    }
}
