package sv.com.devskodigo.dao.toxlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sv.com.devskodigo.DataOperations;
import sv.com.devskodigo.dto.ReportDetailDto;
import sv.com.devskodigo.dto.ReportSummaryDto;

import java.io.*;
import java.util.*;

public class ReportDetailToXlsx implements DataOperations<ReportDetailDto> {
    private final String path = "ReportDetails.xlsx";
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Map<String, Object[]> data;
    private int recordCounter = 1; //1 is Spreadsheet's Header
    private List cellDataList;
    private Iterator rowIterator;
    private Object[]header=new Object[]{"ID", "DateTime", "Classification", "Description", "Report"};
    File file;

    public static void main(String[] args) {

        ReportDetailToXlsx toXlsx = new ReportDetailToXlsx();
        ReportDetailDto rd = new ReportDetailDto();
        rd.setDetailId(1);
        rd.setDetailClassification("my Clasification");
        rd.setDetailDescription("my Description");
        rd.setDetailDateTime(new Date());
        rd.setReportSummary(new ReportSummaryDto());
        rd.getReportSummary().setReportId(1);
        toXlsx.addData(rd);
        toXlsx.read();
    }

    public ReportDetailToXlsx() {
        readDataset();
    }

    @Override
    public void readDataset() {

        file = new File(path);
        if (!file.exists())//If not exist make it
        {
            try {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("ReportDetails");
                data = new TreeMap<String, Object[]>();
                data.put("1", header);
                //it is necessary to iterate the data to sabe it into a row
                Set<String> keyset = data.keySet();
                int rownum = 0;
                for (String key : keyset) {
                    Row row = sheet.createRow(rownum++);
                    Object[] objArr = data.get(key);
                    int cellnum = 0;
                    for (Object obj : objArr) {
                        Cell cell = row.createCell(cellnum++);
                        if (obj instanceof String) //name
                            cell.setCellValue((String) obj);
                        else if (obj instanceof Integer) //id
                            cell.setCellValue((Integer) obj);
                        else if (obj instanceof Date) //float
                            cell.setCellValue(((Date) obj).toString());
                        else if (obj instanceof ReportSummaryDto) //float
                            cell.setCellValue(((ReportSummaryDto) obj).getReportId());
                    }
                }
                FileOutputStream out = new FileOutputStream(file);
                workbook.write(out);
                out.close();
            } catch (Exception ioe) {
                System.out.println("Error during reading dataset routine");
                ioe.printStackTrace();
            }
        }
    }

    @Override
    public void addData(ReportDetailDto t) {
        String localRecordCounter;
        try {
            workbook = new XSSFWorkbook(path);
            sheet = workbook.getSheet("ReportDetails");
            int rownum = sheet.getLastRowNum()+1;
            data = new TreeMap<String, Object[]>();
            //"ID", "DateTime", "Classification", "Description", "Report"
            data.put(String.valueOf(rownum),
                    new Object[]{t.getDetailId(),t.getDetailDateTime(),t.getDetailClassification(),t.getReportSummary()});
            //it is necessary to iterate the data to sabe it into a row
            Set<String> keyset = data.keySet();
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String) //name
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer) //id
                        cell.setCellValue((Integer) obj);
                    else if (obj instanceof Date) //float
                        cell.setCellValue(((Date) obj).toString());
                    else if (obj instanceof ReportSummaryDto) //float
                        cell.setCellValue(((ReportSummaryDto) obj).getReportId());
                }
            }

            System.out.println("Your data is saved");
        } catch (Exception e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }

    }

    public void read(){
        try {
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateData(ReportDetailDto reportDetail) {

    }

    @Override
    public void searchData(int id) {
        int entityTarget;
        int updateRowOperation = 0;
        boolean dataFound = false;


    }

    @Override
    public void deleteData(int id) {

    }

    @Override
    public List<ReportDetailDto> dataList() {

        try {
            //searchData routine
            List<ReportDetailDto> reports = new ArrayList<>();
            rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = (Row) rowIterator.next();
                Iterator iterator = row.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    Cell cell = (Cell) iterator.next();
                    cellTempList.add(cell);
                }
                cellDataList.add(cellTempList);
            }
            if (cellDataList.size() > 0) {
                //print the content of the cellDataList
                for (int i = 0; i < cellDataList.size(); i++) {
                    List cellTempList = (List) cellDataList.get(i);
                    for (int j = 0; j < cellTempList.size(); j++) {
                        Cell cell = (Cell) cellTempList.get(j);
                        String stringCellValue = cell.toString();
                        System.out.print(stringCellValue + "\t");
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.out.println("An error ocurred");
        }
        return null;
    }
}
