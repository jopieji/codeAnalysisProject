package com.analysis;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

// class to Fetch remote repository and clone it locally
public class Fetch {
    // PRIVATE FIELDS
    private String repoUrl;
    private Credentials credentials;
    private File outFile;

    // Constructor
    public Fetch(String repoUrl) {
        this.repoUrl = repoUrl;
        this.credentials = new Credentials();
        this.outFile = null;
    }

    // get file directory where output will go
    public File getOutFile() {
        return this.outFile;
    }

    // make new folder for cloned repo
    private File createNewFolder() throws IOException {
        FileUtils.deleteDirectory(new File("./clonedRepo"));
        File fp = new File("./clonedRepo");
        this.outFile = fp;
        if (fp.mkdir()) {
            System.out.println("Directory created ");
        } else {
            System.out.println("Directory cannot be created");
        }
        return fp;
    }

    // cloning function
    public Git cloneRepository() throws GitAPIException {
        System.out.println("Cloning repository located at " + repoUrl);
        // clone repo with uri, output dir, and credentials
        try (Git repo = Git.cloneRepository()
                .setURI(this.repoUrl)
                .setDirectory(this.createNewFolder())
                .setCredentialsProvider(credentials.createCredentialsProvider())
                .call()) {
            System.out.println("Repository fetched: " + this.repoUrl);
            return repo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GitAPIException e) {
            System.out.println("Clone failed! Make sure your repository is public! Try again");
            return null;
        }
    }
}
