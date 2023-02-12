package com.analysis;

import org.eclipse.jgit.api.errors.GitAPIException;

public class Main {
    public static void main(String[] args) throws GitAPIException {
        // eventually implement with keyword arguments
        // private repo to throw auth exception
        Fetch w = new Fetch("https://github.com/jopieji/se350");
        Fetch f = new Fetch("https://github.com/jopieji/instructional-guide");
        w.cloneRepository();
        // use JGit instead to show new directory created or contents
        System.out.println("Done!");
    }
}
