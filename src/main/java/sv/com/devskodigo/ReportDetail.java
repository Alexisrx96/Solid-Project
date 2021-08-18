package sv.com.devskodigo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ReportDetail implements DataOperations {

    @Getter
    @Setter
    private static int x;
    @Getter
    @Setter
    private int detailId;
    @Getter
    @Setter
    private Date detailDateTime;
    @Getter
    @Setter
    private String detailClassification;
    @Getter
    @Setter
    private String detailDescription;
    /*
    @Getter
    @Setter
    private int reportId;
    */
    @Getter
    @Setter
    private ReportSummary reportSummary;

    public ReportDetail(ReportSummary reportSummary) {
        this.reportSummary = reportSummary;
    }

    public ReportDetail() {
    }

    @Override
    public void readDataset(){

    }

    @Override
    public void addData(){

    }

    @Override
    public void updateData(){

    }

    @Override
    public void searchData(){

    }

    @Override
    public void deleteData(){

    }

    @Override
    public void updateStatus(){

    }
}
