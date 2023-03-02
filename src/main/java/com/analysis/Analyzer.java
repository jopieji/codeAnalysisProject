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

    public File pointToMain() {
        String s = this.outFile.toString();
        s += "/src/main/java/com/analysis/Main.java";
        return new File(s);
    }

    private PMDConfiguration pmdConfiguration() {
        PMDConfiguration config = new PMDConfiguration();
        config.setInputFilePath(this.pointToMain().toPath());
        config.addRuleSet("rulesets/java/quickstart.xml");
        config.setReportFormat("xml");
        config.setReportFile("pmd-report.xml");

        return config;
    }
}
