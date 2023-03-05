package com.analysis;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Analyzer {

    private File outFile;
    private String filename;

    public Analyzer(File f, String filename) {
        this.outFile = f;
        this.filename = filename;
    }

    public void runPMD() {
        PMD.runPmd(this.pmdConfiguration());
    }

    public List<Path> createPathList(String dir) {
        List<Path> result = new ArrayList<Path>();
        try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
            paths.filter(Files::isRegularFile)
                    .filter(item -> item.toString().endsWith(".java"))
                    .forEach(item -> result.add(item));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private PMDConfiguration pmdConfiguration() {
        PMDConfiguration config = new PMDConfiguration();
        config.setInputPathList(createPathList(outFile.toString()));
        // singular file
        // config.setInputFilePath(this.pointToMain().toPath());
        config.addRuleSet("rulesets/java/quickstart.xml");
        config.setReportFormat("csv");
        config.setReportFile(filename + ".csv");

        return config;
    }
}
