package com.analysis;

import io.github.cdimascio.dotenv.Dotenv;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

// class to provide Github credentials for cloning action
public class Credentials {

    // fields to access with getters
    private static String user;
    private static String pass;

    // constructor to fetch credentials on creation set them
    public Credentials() {
        Dotenv dotenv = Dotenv.load();
        this.user = dotenv.get("GITHUB_USER");
        this.pass = dotenv.get("GITHUB_PASS");
        System.out.println("Cloning via user " + user);
    }

    // function to pass back CredentialsProvider
    public CredentialsProvider createCredentialsProvider() {
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(user, pass);
        return cp;
    }

}
