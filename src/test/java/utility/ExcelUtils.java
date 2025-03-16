package utility;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static String getCellValue(String filePath, String sheetName, int rowNum, int colNum) {
        String value = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);

            // Convert cell to string
            if (cell != null) {
                value = cell.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int getRowCount(String filePath, String sheetName) {
        int rowCount = 0;
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            rowCount = sheet.getLastRowNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    // Add more methods if needed, e.g., to read entire row data, etc.
}
