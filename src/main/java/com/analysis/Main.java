package com.analysis;

import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws GitAPIException {
        // eventually implement with keyword arguments
        // private repo to throw auth exception

        // take input from user using Scanner
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your url: ");
        String url = sc.next();
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
        Analyzer analyzer = new Analyzer(f);
        analyzer.runPMD();

        // maybe use JGit instead to show new directory created or contents
        System.out.println("Done! Check the pmd-report.csv file for output");
    }
}
