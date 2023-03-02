package com.analysis;

import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws GitAPIException {
        // eventually implement with keyword arguments
        // private repo to throw auth exception

        // take input from user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your url: ");
        String url = sc.next();

        // instantiate Fetch object with user URL
        Fetch fetch = new Fetch(url);
        fetch.cloneRepository();

        File f = fetch.getOutFile();

        // run PMD
        Analyzer analyzer = new Analyzer(f);
        analyzer.runPMD();

        // maybe use JGit instead to show new directory created or contents
        System.out.println("Done!");
    }
}
