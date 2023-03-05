package com.analysis;

import com.aspose.cells.Workbook;

import java.io.File;
import java.util.Scanner;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // eventually implement with keyword arguments
        // private repo to throw auth exception

        // take URL input from user using Scanner
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your url: ");
        String url = sc.next();

        // computer with CSV or Excel file format
        String outputType = "";
        while (!outputType.equals("c") && !outputType.equals("x")) {
            System.out.println("Please enter a (c) for csv or (x) for excel: ");
            outputType = sc.next();
        }

        // allow user to name the file
        System.out.print("What would you like the output file to be called: ");
        String filename = sc.next();

        // ruleset selection
        System.out.println("What ruleset would you like to use?\n" + Analyzer.getRulesets());
        String ruleset = sc.next();
        while (!(Analyzer.getRulesets().contains(ruleset))) {
            System.out.print("Please select a valid ruleset: ");
            ruleset = sc.next();
        }

        // closed scanner
        sc.close();

        // instantiate Fetch object with user URL
        Fetch fetch = new Fetch(url);
        // clone repository to local using Fetch class
        fetch.cloneRepository();

        // get filepath from Fetch class for analyzing repo files
        File f = fetch.getOutFile();

        // run PMD using Analyzer class, which picks out java
        // files and analyzes them for code style
        Analyzer analyzer = new Analyzer(f, filename, ruleset);
        analyzer.runPMD();

        if (outputType.equals("x")) {
            // save to excel
            CSVToExcel converter = new CSVToExcel(filename);
            Workbook wb = converter.csvToExcel(filename + ".csv");
            // show user that file has been created
            System.out.format("Done! Check the %s.xlsx file for output", filename);
            try {
                // delete csv; csv used to generate excel file
                Files.deleteIfExists(Paths.get("./" + filename + ".csv"));
                System.out.println("Successful delete");
            } catch (NoSuchFileException e) {
                System.out.println("No file exists");
            }
        } else {
            // print csv output if excel file not made.
            System.out.format("Done! Check the %s.csv file for output", filename);
        }
    }
}
