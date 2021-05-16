package ExcelBase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelSQL {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Path file = Path.of("util/src/main/resources/templates/Ж-54 Э Журнал учета средств измерений.xlsx").toAbsolutePath();
            readFromExcel(file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromExcel(Path file) throws IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_lab_optima?useUnicode=true&serverTimezone=UTC", "root", "123123");
        PreparedStatement stmt;

        XSSFSheet excelSheet = new XSSFWorkbook(new FileInputStream(String.valueOf(file))).getSheet("Лист3 (2)");
        XSSFRow row;

        String sql;
        String factory_number = "";
        String inventory_number = "";
        String name = "";
        Date verification_date = new Date();
        Date verification_date_end = new Date();
        String verification_number = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int startRow = 5;
        char c;

        sql = "Insert into `spring_lab_optima`.`equipments` " + "(`factory_number`, `inventory_number`, `name`, `verification_date`, `verification_date_end`, `verification_number`) " + "values (?,?,?,?,?,?) ";
        stmt = connection.prepareStatement(sql);

        while(true) {
            row = excelSheet.getRow(startRow);
            try {
                if(row.getCell(0) == null) {
                    row.createCell(0);
                } else {
                    if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        if(! row.getCell(0).getStringCellValue().startsWith("Вспомогательное"))
                            if(! row.getCell(0).getStringCellValue().startsWith("Средства")) {
                                if(row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                                    factory_number = String.valueOf((int) row.getCell(4).getNumericCellValue());
                                else factory_number = row.getCell(4).getStringCellValue();
                                inventory_number = row.getCell(0).getStringCellValue();
                                name = row.getCell(1).getStringCellValue() + row.getCell(2).getStringCellValue() + row.getCell(3).getStringCellValue();
                                if(! (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_NUMERIC))
                                    verification_number = row.getCell(5).getStringCellValue();
                                else verification_number = String.valueOf((int) row.getCell(5).getNumericCellValue());
                                if(row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                    verification_date = row.getCell(6).getDateCellValue();
                                    verification_date_end = row.getCell(6).getDateCellValue();
                                }
                            }
                    }
                    connection.setAutoCommit(false);
                    factory_number = factory_number.trim();
                    if(factory_number.length() > 0) {
                        c = factory_number.charAt(0);
                        if(c == '№') stmt.setString(1, factory_number.substring(1).trim().replaceAll("\n", " "));
                        else stmt.setString(1, factory_number.trim().replaceAll("\n", " "));
                    }
                    inventory_number = inventory_number.trim();
                    if(inventory_number.length() > 0) {
                        c = inventory_number.charAt(0);
                        if(c == '№') stmt.setString(2, inventory_number.substring(1).trim().replaceAll("\n", " "));
                        else stmt.setString(2, inventory_number.trim().replaceAll("\n", " "));
                    }
                    stmt.setString(3, name.replaceAll("\n", " "));
                    verification_number = verification_number.trim();
                    if(verification_number.length() > 0) {
                        c = verification_number.charAt(0);
                        if(c == '№') stmt.setString(6, verification_number.substring(1).trim().replaceAll("\n", " "));
                        else stmt.setString(6, verification_number.trim().replaceAll("\n", " "));
                    }
                    stmt.setDate(4, java.sql.Date.valueOf(formatter.format(verification_date)));
                    stmt.setDate(5, java.sql.Date.valueOf(formatter.format(verification_date_end)));
                    stmt.addBatch();
                    stmt.executeBatch();
                    connection.commit();
                }
            } catch(NullPointerException e) {
                System.out.println("Данные успешно добавленны в БД!");
                break;
            }
            startRow++;
        }
    }
}
