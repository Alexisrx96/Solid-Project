package sv.com.devskodigo.model.dao.toxlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sv.com.devskodigo.DataOperations;
import sv.com.devskodigo.model.dto.UserDto;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class UserToXlsx implements DataOperations<UserDto> {

    private int menuOption;
    private Scanner rawData = new Scanner(System.in);
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Map<String, Object[]> data;
    int recordCounter = 1; //1 is Spreadsheet's Header
    List<List<String>> cellDataList;
    Iterator<Row> rowIterator;

    public UserToXlsx(){
        readDataset();
    }

    @Override
    public void readDataset() {
        menuOption = 0;
        rawData = new Scanner(System.in);
        try{
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("User");
            data = new TreeMap<String, Object[]>();
            data.put("1", new Object[] {"ID", "FIRST_NAME", "LAST_NAME", "ACCOUNT_NAME", "PASSWORD", "STATUS"});
            System.out.println("User dataset loaded");
        }catch(Exception ioe){
            System.out.println("Error during reading dataset routine");
            ioe.printStackTrace();
        }
    }


    @Override
    public void addData(UserDto t) {
        String localRecordCounter;
        try{
            localRecordCounter = String.valueOf(recordCounter++);
            data.put(localRecordCounter, new Object[] {t.getId(), t.getFirstName(), t.getLastName(), t.getAccountName(), t.getPassword(), t.getStatus()});

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
            FileOutputStream out = new FileOutputStream(new File("user.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Your data is saved");
        }catch(Exception e){
            System.out.println("An error has ocurred");
            e.printStackTrace();
        }
    }

    @Override
    public void updateData(UserDto userDto) {

    }
    @Override
    public void searchData(int id) {
        int UserTarget;
        int updateRowOperation = 0;
        boolean dataFound = false;

    }

    @Override
    public List<UserDto> dataList() {
        try{
            System.out.println("Displaying current getList of users");
            //searchData routine
            List<UserDto> user = new ArrayList<>();
            rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
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
                    List cellTempList = cellDataList.get(i);
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
