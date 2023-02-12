package com.analysis;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;

// class to Fetch remote repository and clone it locally
public class Fetch {
    // PRIVATE FIELDS
    private String repoUrl;

    // make new folder for cloned repo
    private static File createNewFolder() throws IOException {
        File path = File.createTempFile("testFile", "");
        if (!path.delete()) {
            throw new IOException("Could not remove temp file " + path);
        }
        return path;
    }

    // cloning function
    public Git cloneRepository(String remoteURL) throws GitAPIException {
        System.out.println("Cloning repository located at " + remoteURL);

        try (Git repo = Git.cloneRepository()
                .setURI(remoteURL)
                .setDirectory(createNewFolder())
                .call()) {
            System.out.println("Repository fetched: " + repo.getRepository().getDirectory());
            return repo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters and Setters
    public String getRepoUrl() {
        return repoUrl;
    }
    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }






}
