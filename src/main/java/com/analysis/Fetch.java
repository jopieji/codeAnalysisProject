package com.analysis;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;

// class to Fetch remote repository and clone it locally
public class Fetch {
    // PRIVATE FIELDS
    private String remoteUrl;

    // make new folder for cloned repo
    private static File createNewFolder() throws IOException {
        File localPath = File.createTempFile("testFile", "");
        if (!localPath.delete()) {
            throw new IOException("Could not remove temp file " + localPath);
        }
        return localPath;
    }

    // cloning function
    private void cloneRepository(String remoteURL) throws GitAPIException {
        System.out.println("Cloning repository at " + remoteURL);

        try (Git repo = Git.cloneRepository()
                .setURI(remoteURL)
                .setDirectory(createNewFolder())
                .call()) {
            System.out.println("Repository fetched: " + repo.getRepository().getDirectory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
        // clean up repository to prevent memory leak
    }




    // Getters and Setters
    public String getRemoteUrl() {
        return remoteUrl;
    }
    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }






}
