package com.analysis;

import org.eclipse.jgit.api.errors.GitAPIException;

public class Main {
    public static void main(String[] args) throws GitAPIException {
        Fetch f = new Fetch("https://github.com/jopieji/finDash");
        f.cloneRepository();
        // use JGit instead to show new directory created or contents
        System.out.println("Done!");
    }
}
