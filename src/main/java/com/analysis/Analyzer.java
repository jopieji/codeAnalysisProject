package com.analysis;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

import java.io.File;


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

    private PMDConfiguration pmdConfiguration() {
        PMDConfiguration config = new PMDConfiguration();

        config.setInputFilePath(this.outFile.toPath());
        config.addRuleSet("rulesets/java/quickstart.xml");
        config.setReportFormat("csv");
        config.setReportFile("pmd-report.csv");

        return config;
    }
}
