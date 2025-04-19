package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

    private String path;
    private FileInputStream fis;
    private Workbook workbook;

    public XLUtility(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            System.out.println("Unable to read Excel file: " + path);
            e.printStackTrace();
        }
    }

    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum();
    }

    public int getCellCount(String sheetName, int rowNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        return row.getLastCellNum();
    }

    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell); // handles all types (string, numeric, etc.)
    }

    public void closeWorkbook() {
        try {
            workbook.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("Error closing Excel workbook.");
            e.printStackTrace();
        }
    }
}
