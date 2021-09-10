package sv.com.devskodigo.controller;

import sv.com.devskodigo.model.dao.ReportSummaryDao;
import sv.com.devskodigo.model.dto.ReportSummaryDto;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportSummaryController implements ICrudOperations {
    private Date reportSummaryDateTime;
    private int flightId = 0;
    private int userId = 0;
    private int statusId = 0;

    private Scanner rawData;
    private int requestedAction;
    private int targetId = 0;
    private ReportSummaryDto reportSummary;

    public ReportSummaryController(int ra){
        this.requestedAction = ra;
        this.crudPipeline();

    }

    @Override
    public void crudPipeline(){
        switch(requestedAction){
            case 1: //insert
                this.getData();
                this.saveData();
                break;

            case 2://display
                this.viewData();
                break;

            case 3: //delete
                targetId = this.searchData();
                if(targetId > 0){
                    this.deleteData(targetId);
                }
                break;

            case 4: //update
                targetId = this.searchData();
                if(targetId > 0){
                    this.getData();
                    this.updateData(targetId);
                }
                break;
        }
    }

    @Override
    public void getData(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rawData = new Scanner(System.in);
        System.out.println("Please type the below requested about the Report: ");
        System.out.println("Report's date/time:");
        try{
            reportSummaryDateTime = dateFormat.parse(rawData.nextLine());
        }catch(ParseException pe){
            System.out.println("Error parsing date-time information");
        }
        System.out.println("Flight Id:");
        flightId = rawData.nextInt();
        rawData.nextLine();
        System.out.println("User Id:");
        userId = rawData.nextInt();
        rawData.nextLine();
        System.out.println("Status Id:");
        statusId = rawData.nextInt();
        rawData.nextLine();

    }

    @Override
    public void saveData(){
        ReportSummaryDao reportSummaryDao =  new ReportSummaryDao();
        reportSummaryDao.insert(new ReportSummaryDto(0, this.reportSummaryDateTime, this.flightId,
                this.userId, this.statusId));
    }

    @Override
    public void viewData(){
        ReportSummaryDao reportSummaryDao = new ReportSummaryDao();
        for(var c: reportSummaryDao.getList()){
            System.out.println(c);
        }
    }

    @Override
    public int searchData(){
        rawData = new Scanner(System.in);
        int idFound = 0;
        System.out.println("Please type a valid id:");
        int idSearch = rawData.nextInt();
        ReportSummaryDao reportSummaryDao = new ReportSummaryDao();
        reportSummary = reportSummaryDao.read(idSearch);
        if(reportSummary == null){
            System.out.println("No records found");
        }
        else{
            System.out.println("====================");
            System.out.println("Displaying Report Summary information found:");
            System.out.println("Date/Time: "+"\t"+reportSummary.getReportDateTime());
            System.out.println("Flight Id: "+"\t"+reportSummary.getFlightId());
            System.out.println("User Id: "+"\t"+reportSummary.getUserId());
            System.out.println("Current Report's Status: "+"\t"+reportSummary.getReportStatusId());
            System.out.println("====================");
            idFound = reportSummary.getReportId();
        }
        return idFound;
    }

    @Override
    public void deleteData(int id){
        ReportSummaryDao reportSummaryDao = new ReportSummaryDao();
        reportSummaryDao.delete(id);
        System.out.println("Record deleted");

    }

    @Override
    public void updateData(int id){
        ReportSummaryDao reportSummaryDao = new ReportSummaryDao();
        reportSummaryDao.update(new ReportSummaryDto(id, this.reportSummaryDateTime, this.flightId,
                this.userId, this.statusId));
        System.out.println("Record updated");
    }

}
