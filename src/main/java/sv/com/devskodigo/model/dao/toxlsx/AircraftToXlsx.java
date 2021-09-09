package sv.com.devskodigo.model.dao.toxlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sv.com.devskodigo.DataOperations;
import sv.com.devskodigo.model.dto.AircraftDto;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class AircraftToXlsx implements DataOperations<AircraftDto> {
    private int menuOption;
    private Scanner rawData = new Scanner(System.in);

    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Map<String, Object[]> data;
    int recordCounter = 1; //1 is Spreadsheet's Header
    List cellDataList;
    Iterator rowIterator;

    public AircraftToXlsx(){
        readDataset();
    }

    @Override
    public void readDataset() {
        menuOption = 0;
        rawData = new Scanner(System.in);
        try{
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Aircraft");
            data = new TreeMap<String, Object[]>();
            data.put("1", new Object[] {"ID", "AIRCRAFT_MODEL", "PASSENGER", "MAX_FUEL", "STATUS"});
            System.out.println("Aircraft dataset loaded");
        }catch(Exception ioe){
            System.out.println("Error during reading dataset routine");
            ioe.printStackTrace();
        }
    }

    @Override
    public void addData(AircraftDto t) {
        String localRecordCounter;
        try{
            localRecordCounter = String.valueOf(recordCounter++);
            data.put(localRecordCounter, new Object[] {t.getId(), t.getModel(), t.getPassengersCapacity(), t.getMaxFuel(), t.getStatus()});

            //it is necesary to iterate the data to savbe it into a row
            Set<String> keyset = data.keySet();
            int rownum = 0;
            for (String key : keyset)
            {
                Row row = sheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr)
                {
                    Cell cell = row.createCell(cellnum++);
                    if(obj instanceof String) //name
                        cell.setCellValue((String)obj);
                    else if(obj instanceof Integer) //id
                        cell.setCellValue((Integer)obj);
                    else if(obj instanceof Float) //float
                        cell.setCellValue((Float)obj);
                }
            }
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("Aircraft.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Your data is saved");
        }catch(Exception e){
            System.out.println("An error has ocurred");
            e.printStackTrace();
        }

    }

    @Override
    public void updateData(AircraftDto aircraftDto) {

    }

    @Override
    public void searchData(int id) {
        int aircraftTarget;
        int updateRowOperation = 0;
        boolean dataFound = false;
    }


    @Override
    public List<AircraftDto> dataList() {
        try{
            System.out.println("Displaying current getList of Aircraft");
            //searchData routine
            List<AircraftDto> aircraft = new ArrayList<>();
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
            if(cellDataList.size() > 0){
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
        }catch(Exception e){
            System.out.println("An error ocurred");
        }

        return null;
    }


    @Override
    public void deleteData(int id) {

    }
}
