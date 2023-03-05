package com.analysis;

import com.aspose.cells.LoadOptions;
import com.aspose.cells.Workbook;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.SaveFormat;

public class CSVToExcel {
    // user's selected name of file
    private String filename;

    //
    public CSVToExcel(String filename) {
        this.filename = filename;
    }

    //
    public Workbook csvToExcel(String fileString) throws Exception {
        // Load CSV file
        LoadOptions loadOptions = new LoadOptions(FileFormatType.CSV);
        Workbook workbook = new Workbook(fileString, loadOptions);

        // Save as Excel file at user specified filename
        workbook.save(filename + ".xlsx", SaveFormat.XLSX);
        return workbook;
    }
}
