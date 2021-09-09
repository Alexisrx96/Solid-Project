package sv.com.devskodigo.model.dao.toxlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sv.com.devskodigo.DataOperations;
import sv.com.devskodigo.model.dto.FlightDto;
import sv.com.devskodigo.model.dto.ReportSummaryDto;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class FlightToXlsx implements DataOperations<FlightDto> {

    private int menuOption;
    private Scanner rawData;
    private String dtInput; //tempVar to get DateTimeInputData
private final String filePath="";
private Object[] header;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Map<String, Object[]> data;
    int recordCounter; //1 is Spreadsheet's Header
    List cellDataList;
    Iterator rowIterator;

    @Override
    public void readDataset() {

        menuOption = 0;
        rawData = new Scanner(System.in);
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
    public void addData(FlightDto flightDto) {

    }

    @Override
    public void updateData(FlightDto flightDto) {

    }

    @Override
    public List<FlightDto> dataList() {
        return null;
    }

    @Override
    public void searchData(int id) {

    }

    @Override
    public void deleteData(int id) {

    }
}
