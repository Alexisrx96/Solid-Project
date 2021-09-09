package sv.com.devskodigo.model.dao.toxlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sv.com.devskodigo.DataOperations;
import sv.com.devskodigo.model.dto.ReportDetailDto;
import sv.com.devskodigo.model.dto.ReportSummaryDto;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportDetailToXlsx implements DataOperations<ReportDetailDto> {
    private final String filePath = "ReportDetails.xlsx";
    private final Object[] header = new Object[]{"ID", "DateTime", "Classification", "Description", "Report"};
    private Map<String, Object[]> data;
    private Iterator rowIterator;

    public ReportDetailToXlsx() {
        readDataset();
    }

    public static void main(String[] args) {

        ReportDetailToXlsx toXlsx = new ReportDetailToXlsx();
        ReportDetailDto rd = new ReportDetailDto();
        rd.setDetailId(1);
        rd.setDetailClassification("my Clasification");
        rd.setDetailDescription("my Description");
        rd.setDetailDateTime(new Date());
        //rd.setReportSummary(new ReportSummaryDto());
        //rd.getReportSummary().setReportId(1);
        toXlsx.addData(rd);
        toXlsx.read();
    }

    @Override
    public void readDataset() {

        File file = new File(filePath);
        if (!file.exists())//If not exist make it
        {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("ReportDetails");
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
                            cell.setCellValue(obj.toString());
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
        List<ReportDetailDto> dataList = dataList();
        dataList.add(t);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("ReportDetails");
            data = new TreeMap<String, Object[]>();
            data.put("1", header);
           /* for (int i = 0; i < dataList.size(); i++) {
                final ReportDetailDto report = dataList.get(i);
                data.put(String.valueOf(i + 2),
                        new Object[]{report.getDetailId(), report.getDetailDateTime(),
                                report.getDetailClassification(), report.getDetailDescription(),
                                report.getReportSummary()});
            }*/
            //it is necessary to iterate the data to sabe it into a row
            int rownum = 0;
            Set<String> keyset = data.keySet();
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String) //name
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer) //id
                        cell.setCellValue((Integer) obj);
                    else if (obj instanceof Date) //float
                        cell.setCellValue(formatter.format((Date) obj));
                    else if (obj instanceof ReportSummaryDto) //float
                        cell.setCellValue(((ReportSummaryDto) obj).getReportId());
                }
            }
            FileOutputStream outputStream = new FileOutputStream(new File(filePath));
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

            System.out.println("Your data is saved");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void read() {
        try {
            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
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

        List<ReportDetailDto> reports = new ArrayList<>();
        try {
            //searchData routine

            Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            Sheet sheet = workbook.getSheetAt(0);
            rowIterator = sheet.rowIterator();
            if (rowIterator.hasNext())//Skips header
                rowIterator.next();
            while (rowIterator.hasNext()) {
                ReportDetailDto report = new ReportDetailDto();
                reports.add(report);
                Row row = (Row) rowIterator.next();
                int i = 0;
                Iterator iterator = row.cellIterator();
                while (iterator.hasNext()) {
                    String cell = iterator.next().toString();
                    switch (i) {
                        case 0:
                            report.setDetailId((int) Double.parseDouble(cell));
                            break;
                        case 1:
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                            try {
                                report.setDetailDateTime(formatter.parse(cell));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            report.setDetailClassification(cell);
                            break;
                        case 3:
                            report.setDetailDescription(cell);
                            break;
                        case 4:
                            ReportSummaryDto rs = new ReportSummaryDto();
                            rs.setReportId((int) Double.parseDouble(cell));
                            //report.setReportSummary(rs);
                            break;
                    }
                    i++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reports;
    }
}
