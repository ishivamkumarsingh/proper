package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateTestDataExcel {

    public static void createSignInTestData(String filePath) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Username");
        header.createCell(1).setCellValue("Password");

        // Create a sample row of data
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("validuser@test.com");
        row1.createCell(1).setCellValue("validPass");

        // Another row
        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("invaliduser@test.com");
        row2.createCell(1).setCellValue("wrongPass");

        // Write to file
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Excel test data created at: " + filePath);
    }

    // Optional main method if you want to run this utility independently
    public static void main(String[] args) {
        String filePath = "src/test/resources/testdata/SignInTestData.xlsx";
        createSignInTestData(filePath);
    }
}
