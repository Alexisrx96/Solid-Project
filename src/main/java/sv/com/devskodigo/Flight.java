package sv.com.devskodigo;

/*
name: Flight.java
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
import sv.com.devskodigo.dto.FlightDto;

import java.io.File;
import java.io.FileOutputStream;
//import java.time.LocalDateTime;
import java.util.*;
import java.text.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flight implements DataOperations <FlightDto>{

    private int menuOption;
    private Scanner rawData;
    private String dtInput; //tempVar to get DateTimeInputData

    @Getter @Setter
    private int flightId;
    @Getter @Setter
    private String flightDescriptor;
    @Getter @Setter
    private Date flightDateTimeDep;
    @Getter @Setter
    private Date flightDateTimeArr;
    @Getter @Setter
    private int flightStatus;
    @Getter @Setter
    private String flightWeatherRpt;
    @Getter @Setter
    private int aircfratId;
    @Getter @Setter
    private int airlineId;
    @Getter @Setter
    private int flightCountryIdDep;
    @Getter @Setter
    private int flightCityIdDep;
    @Getter @Setter
    private int flightCountryIdArr;
    @Getter @Setter
    private int flightCityIdArr;
    @Getter @Setter
    private int userId;

    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Map<String, Object[]> data;
    int recordCounter; //1 is Spreadsheet's Header
    List cellDataList;
    Iterator rowIterator;

    //constructor method
    public Flight(){
        recordCounter = 1; //1 is Spreadsheet's Header
    }

    //open dataset

    @Override
    public void readDataset(){
        menuOption = 0;
        rawData = new Scanner(System.in);
        try{
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Flight");
            data = new TreeMap<String, Object[]>();
            data.put("1", new Object[] {"ID", "DESCRIPTOR", "DT DEPARTURE", "DT ARRIVE", "STATUS",
                    "WEATHER RPT", "AIRCRAFT", "AIRLINE", "COUNTRY DEP", "CITY DEP", "COUNTRY ARR",
                    "CITY ARR", "USER"});
            System.out.println("Flight dataset loaded");
        }catch(Exception ioe){
            System.out.println("Error during reading dataset routine");
            ioe.printStackTrace();
        }
    }//end of readDataSet()

    public void selectOption(){

    }//end of selectOption method

    public void getData(){
        recordCounter++;
        FlightDto flight = new FlightDto();
        this.readDataset();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        System.out.println("Pleae type id");
        this.setFlightId(rawData.nextInt());
        //numeric input data does not send enter at the end
        rawData.nextLine();
        System.out.println("Please type Flight Descriptor");
        this.setFlightDescriptor(rawData.nextLine());
        try{
            System.out.println("Please type Flight Date Time of Departure (yyyy-mm-dd hh:mm)");
            dtInput = rawData.nextLine();
            this.setFlightDateTimeDep(ft.parse(dtInput));
            System.out.println("Please type Flight Date Time of Arrive (yyyy-mm-dd hh:mm)");
            dtInput = rawData.nextLine();
            this.setFlightDateTimeDep(ft.parse(dtInput));
        }catch(ParseException pe){
            System.out.println("an error ocurred during DateTime Input Data");
            pe.printStackTrace();
        }
        System.out.println("Please type Flight Status(0->Cancelled, 1->OnTime, 2->Delayed");
        flight.setFlightStatus(rawData.nextInt());
        rawData.nextLine();
        System.out.println("Please type Aircraft id");
        flight.setAircfratId(rawData.nextInt());
        rawData.nextLine();
        System.out.println("Please type Airline id");
        flight.setAirlineId(rawData.nextInt());
        rawData.nextLine();
        System.out.println("Please type Country Id of Departure");
        flight.setFlightCountryIdDep(rawData.nextInt());
        rawData.nextLine();
        System.out.println("Please type City Id of Departure");
        flight.setFlightCityIdDep(rawData.nextInt());
        rawData.nextLine();
        System.out.println("Please type Country Id of Arrive");
        flight.setFlightCountryIdArr(rawData.nextInt());
        rawData.nextLine();
        System.out.println("Please type City Id of Arrive");
        flight.setFlightCityIdArr(rawData.nextInt());
        rawData.nextLine();
        System.out.println("Please type Usder ID");
        flight.setUserId(rawData.nextInt());
        rawData.nextLine();
        flight.setFlightWeatherRpt(this.getWeatherRpt());

        this.addData( flight);
    }

    @Override
    public void addData(FlightDto flightDto){
        String localRecordCounter;
        try{

            //just for testing purpose: System.out.println(this.getCountryId()+"-"+this.getCountryName()+"-"+this.getCountryCoords());
            localRecordCounter = String.valueOf(recordCounter++);
            data.put(localRecordCounter, new Object[] {this.getFlightId(), this.getFlightDescriptor(),
                    this.getFlightDateTimeDep(), this.getFlightDateTimeArr(), this.getFlightStatus(),
                    this.getFlightWeatherRpt(), this.getAircfratId(), this.getAirlineId(),
                    this.getFlightCountryIdDep(), this.getFlightCityIdDep(), this.getFlightCountryIdArr(),
                    this.getFlightCityIdArr(), this.getUserId()});

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
                    else if(obj instanceof Date) //dateTimeDeparture
                        cell.setCellValue((Date)obj);
                }
            }
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("flight.xlsx"));
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
            System.out.println("Displaying current list of Flights");
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
            /*
            if(dataFound){
                System.out.println("please type Flight ID you wish to update:");
                entityTarget = rawData.nextInt();
                System.out.println("Please type: 1->Edit Record, 2->Delete Record, 3->Back to Previous Menu");
                updateRowOperation = rawData.nextInt();
                switch(updateRowOperation){
                    case 1: //updateData
                        this.updateData(entityTarget);
                        break;
                    case 2: //deleteData
                        this.deleteData(entityTarget);
                        break;
                    case 3:
                        break;
                }//end of switch(usrOpt)
            }//end of dataFound
             */
        }catch(Exception e){
            System.out.println("An error ocurred");
        }

    }//end of searchData


    @Override
    public void updateData(FlightDto flightDto){
        System.out.println("Cheers from Update: coming next");
    }

    @Override
    public List<FlightDto> dataList() {
        return null;
    }


    @Override
    public void deleteData(int recordId){
        System.out.println("Cheers from delete: coming next");
    }

    public void updateStatus(int recordId){

    }

    public String getWeatherRpt(){
        String rptWeather;
        WeatherAPI weatherapi = new WeatherAPI();
        rptWeather = weatherapi.getForecast();
        return rptWeather;
    }
}