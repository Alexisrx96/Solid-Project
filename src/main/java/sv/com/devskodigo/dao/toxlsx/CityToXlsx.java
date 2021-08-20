package sv.com.devskodigo.dao.toxlsx;

/*
name: City.java
purpose: manage city catalog
author: hftamayo
comments:
1. send a confirmation if dataSet was successfull

 */

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sv.com.devskodigo.dto.CityDto;
import sv.com.devskodigo.DataOperations;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CityToXlsx implements DataOperations<CityDto> {

    private int menuOption;
    private Scanner rawData;

    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Map<String, Object[]> data;
    int recordCounter = 1; //1 is Spreadsheet's Header
    List cellDataList;
    Iterator rowIterator;

    //constructor method
    public CityToXlsx(){
        readDataset();
        selectOption();
    }

    //open dataset

    public void selectOption(){
        System.out.println("City dataset: please type a number equivalent to any of the options above: ");
        int usrContinue = 1;
        do{
            System.out.println("1. Add a Record");
            System.out.println("2. Display data");
            System.out.println("3. Back to Previous Menu");

            try{
                menuOption = rawData.nextInt();
                switch(menuOption){
                    case 1:
                        this.getData(new CityDto());
                        break;
                    case 2:
                        this.searchData(4); //updateData
                        break;
                    case 3:
                        usrContinue = 0;
                        break;
                }

            }catch(InputMismatchException ime){
                System.out.println("Please only type integer numbers");
                ime.printStackTrace();
            }
        }while(usrContinue == 1);

    }//end of selectOption method

    public void getData(CityDto t){
        System.out.println("Pleae type id number");
        t.setCityId(rawData.nextInt());
        //numeric input data does not send enter at the end
        rawData.nextLine();
        System.out.println("Pleae type name");
        t.setCityName((rawData.nextLine()));
        System.out.println("Please type GPS coords");
        t.setCityCoords(rawData.nextFloat());
        rawData.nextLine();
        System.out.println("Please type an existing country id");
        t.setCityId(rawData.nextInt());
        this.addData(t);
    }
    @Override
    public void readDataset(){
        menuOption = 0;
        rawData = new Scanner(System.in);
        try{
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("City");
            data = new TreeMap<String, Object[]>();
            data.put("1", new Object[] {"ID", "CITY_NAME", "GPS_COORDS", "COUNTRY_ID"});
            System.out.println("City dataset loaded");
        }catch(Exception ioe){
            System.out.println("Error during reading dataset routine");
            ioe.printStackTrace();
        }
    }//end of readDataSet()
    @Override
     public void addData(CityDto t){
        String localRecordCounter;
        try{
            //just for testing purpose: System.out.println(this.getCountryId()+"-"+this.getCountryName()+"-"+this.getCountryCoords());
            localRecordCounter = String.valueOf(recordCounter++);
            data.put(localRecordCounter, new Object[] {t.getCityId(), t.getCityName(), t.getCityCoords(), t.getCityId()});

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
                    else if(obj instanceof Integer) //countryid and cityid
                        cell.setCellValue((Integer)obj);
                    else if(obj instanceof Float) //float
                        cell.setCellValue((Float)obj);
                }
            }
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("city.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Your data is saved");
        }catch(Exception e){
            System.out.println("An error has ocurred");
            e.printStackTrace();
        }

    }

    @Override
    public void searchData(int id){
        int entityTarget;
        int updateRowOperation = 0;
        boolean dataFound = false;

        try{
            System.out.println("Displaying current list of countries");
            //searchData routine
            cellDataList = new ArrayList();
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
                dataFound = true;
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

            //ask the user what other operation will need to execute
            if(dataFound){
                System.out.println("please type City ID you wish to update:");
                entityTarget = rawData.nextInt();
                System.out.println("Please type: 1->Edit Record, 2->Delete Record, 3->Back to Previous Menu");
                updateRowOperation = rawData.nextInt();

                switch(updateRowOperation){
                    case 1: //updateData
                        this.updateData(new CityDto());
                        break;
                    case 2: //deleteData
                        this.deleteData(entityTarget);
                        break;
                    case 3:
                        break;
                }//end of switch(usrOpt)
            }//end of dataFound
        }catch(Exception e){
            System.out.println("An error ocurred");
        }

    }//end of searchData


    @Override
    public void updateData(CityDto t){
        System.out.println("Cheers from Update: coming next");
    }

    @Override
    public List<CityDto> dataList() {
        return null;
    }


    public void deleteData(int recordId){
        System.out.println("Cheers from delete: coming next");
    }

}
