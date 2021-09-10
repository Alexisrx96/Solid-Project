package sv.com.devskodigo.controller;

import sv.com.devskodigo.model.dao.ReportDetailDao;
import sv.com.devskodigo.model.dto.ReportDetailDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class ReportDetailController implements ICrudOperations {
    private Date reportDetailDateTime;
    private String reportDetailClassification = "";
    private String reportDetailDescription = "";
    private int reportSummaryId = 0;


    private Scanner rawData;
    private int requestedAction;
    private int targetId = 0;
    private ReportDetailDto reportDetail;

    public ReportDetailController(int ra){
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
        System.out.println("Please type the below requested about the Incident's Detail: ");
        System.out.println("Incident's date/time: (yyyy-MM-dd HH:mm:ss)");
        try{
            reportDetailDateTime = dateFormat.parse(rawData.nextLine());
        }catch(ParseException pe){
            System.out.println("Error parsing date-time information");
        }
        System.out.println("Incident Classification");
        reportDetailClassification = rawData.nextLine();
        System.out.println("Incident Decription");
        reportDetailDescription = rawData.nextLine();
        System.out.println("Report Id:");
        reportSummaryId = rawData.nextInt();
        rawData.nextLine();
    }

    @Override
    public void saveData(){
        ReportDetailDao reportDetailDao =  new ReportDetailDao();
        reportDetailDao.insert(new ReportDetailDto(0, this.reportDetailDateTime, this.reportDetailClassification,
                this.reportDetailDescription, this.reportSummaryId));
    }

    @Override
    public void viewData(){
        ReportDetailDao reportDetailDao = new ReportDetailDao();
        for(var c: reportDetailDao.getList()){
            System.out.println(c);
        }
    }

    @Override
    public int searchData(){
        rawData = new Scanner(System.in);
        int idFound = 0;
        System.out.println("Please type a valid id:");
        int idSearch = rawData.nextInt();
        ReportDetailDao reportDetailDao = new ReportDetailDao();
        reportDetail = reportDetailDao.read(idSearch);
        if(reportDetail == null){
            System.out.println("No records found");
        }
        else{
            System.out.println("====================");
            System.out.println("Displaying Report Incident information found:");
            System.out.println("Date/Time: "+"\t"+reportDetail.getDetailDateTime());
            System.out.println("Incident Class: "+"\t"+reportDetail.getDetailClassification());
            System.out.println("Description: "+"\t"+reportDetail.getDetailDescription());
            System.out.println("Report Summary Id: "+"\t"+reportDetail.getReportSummaryId());
            System.out.println("====================");
            idFound = reportDetail.getDetailId();
        }
        return idFound;
    }

    @Override
    public void deleteData(int id){
        ReportDetailDao reportDetailDao = new ReportDetailDao();
        reportDetailDao.delete(id);
        System.out.println("Record deleted");

    }

    @Override
    public void updateData(int id){
        ReportDetailDao reportDetailDao = new ReportDetailDao();
        reportDetailDao.update(new ReportDetailDto(id, this.reportDetailDateTime, this.reportDetailClassification,
                this.reportDetailDescription, this.reportSummaryId));
        System.out.println("Record updated");
    }

}

