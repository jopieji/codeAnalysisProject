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

    public void setOutFile(File f) {
        this.outFile = f;
    }

    public Analyzer(File f) {
        this.outFile = f;
    }

    public void runPMD() {
        PMD.runPmd(this.pmdConfiguration());
    }

    public File pointToMain() {
        String s = this.outFile.toString();
        s += "/src/main/java/com/analysis/Main.java";
        return new File(s);
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
        System.out.println(result.toString());
        return result;
    }

    private PMDConfiguration pmdConfiguration() {
        PMDConfiguration config = new PMDConfiguration();
        config.setInputPathList(createPathList(this.outFile.toString()));
        // singular file
        // config.setInputFilePath(this.pointToMain().toPath());
        config.addRuleSet("rulesets/java/quickstart.xml");
        config.setReportFormat("csv");
        config.setReportFile("pmd-report.csv");

        return config;
    }
}
