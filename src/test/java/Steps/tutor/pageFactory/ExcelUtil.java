package Steps.tutor.pageFactory;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/** Package: Steps.tutor.pageFactory Generated by: Hoang.Son.Nguyen Generated at: 02.12.2018 2018 */
public class ExcelUtil {
  public final String currentDir = System.getProperty("user.dir");

  // local of Excel file
  public String testDataExcelPath = null;
  public String testDataExcelFileName = null;
  public String sheetName;

  public ExcelUtil(String testDataExcelPath, String sheetName, String testDataExcelFileName) {
    this.testDataExcelFileName = testDataExcelFileName;
    this.testDataExcelPath = testDataExcelPath;
    this.sheetName = sheetName;
    setExcelFileSheet();
  }

  private Workbook workBook;
  private Sheet workSheet;
  private Cell workCell;
  private Row workRow;
  public int rowNumber;
  public int columNumber;
  public int rowCount;
  public int columCount;

  public void setRowCount(int rowCount) {
    this.rowCount = workSheet.getLastRowNum() - workSheet.getFirstRowNum();
  }

  public void setColumCount(int columCount) {
    this.columCount = workSheet.getRow(0).getLastCellNum();
  }

  public int getRowCount() {
    return rowCount;
  }

  public int getColumCount() {
    return columCount;
  }

  public void setRowNumber(int pRowNumber) {
    rowNumber = pRowNumber;
  }

  public int getRowNumber() {
    return rowNumber;
  }

  public void setColumNumber(int pColumNumber) {
    columNumber = pColumNumber;
  }

  public int getColumNumber() {
    return columNumber;
  }

  private Workbook checkExcelType(FileInputStream inputStream) throws IOException {
    Workbook tempWorkbook = null;
    String fileExtentionName = testDataExcelFileName.substring(testDataExcelFileName.indexOf("."));
    if (fileExtentionName.equals(".xls")) {
      tempWorkbook = new HSSFWorkbook(inputStream);
    } else if (fileExtentionName.equals(".xlsx")) {
      tempWorkbook = new XSSFWorkbook(inputStream);
    }
    return tempWorkbook;
  }

  public void setExcelFileSheet() {
    if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
      testDataExcelPath = currentDir + ".//src//test//TestData//";
    } else if (Platform.getCurrent().toString().equalsIgnoreCase("WIN")) {
      testDataExcelPath = ".\\src\\test\\TestData\\";
    }
    System.out.println("NguyenNguyen: " + testDataExcelPath + "\\" + testDataExcelFileName);

    try {
      // Open the Excel file
      FileInputStream excelFileInputStream =
          new FileInputStream(testDataExcelPath + "\\" + testDataExcelFileName);
      workBook = checkExcelType(excelFileInputStream);
      workSheet = workBook.getSheet(sheetName);
    } catch (Exception e) {
      try {
        throw (e);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  public String getCellData(int rowNumber, int columNumber) {
    workCell = workSheet.getRow(rowNumber).getCell(columNumber);
    DataFormatter formatter = new DataFormatter();
    String cellData = formatter.formatCellValue(workCell);

    return cellData;
  }

  public Row getRowData(int rowNumber) {
    workRow = workSheet.getRow(rowNumber);
    return workRow;
  }

  public void setCellData(String value, int rowNumber, int columNumber)
      throws FileNotFoundException {
    workRow = workSheet.getRow(rowNumber);
    workCell = workRow.getCell(columNumber);
    if (workCell == null) {
      workCell = workRow.createCell(columNumber);
      workCell.setCellValue(value);
    } else {
      workCell.setCellValue(value);
    }
    FileOutputStream outputStream = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
    try {
      workBook.write(outputStream);
      outputStream.flush();
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Object[][] excelToArray() {
    Object[][] dataTable = new Object[getRowCount()][getColumCount()];
    for (int i = 0; i < getRowCount(); i++) {
      for (int j = 0; j < getColumCount(); j++) {
        dataTable[i][j] = formatterCellValue(workSheet.getRow(i).getCell(j));
      }
    }
    return dataTable;
  }

  public Object formatterCellValue(Cell cell) {
    Object object = null;
    FormulaEvaluator evaluator = workBook.getCreationHelper().createFormulaEvaluator();
    if (cell != null) {
      switch (evaluator.evaluateFormulaCell(cell)) {
        case BOOLEAN:
          object = cell.getBooleanCellValue();
          break;
        case NUMERIC:
          object = cell.getNumericCellValue();
          break;
        case STRING:
          object = cell.getStringCellValue();
        case FORMULA:
          break;
        case ERROR:
          object = cell.getErrorCellValue();
        case BLANK:
          break;
      }
    }
    return object;
  }
}
