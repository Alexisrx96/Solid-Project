package sv.com.devskodigo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class ReportSummary implements DataOperations {

    //TODO: should have a custom constructor?
    @Getter
    @Setter
    private static int x;

    @Getter
    @Setter
    private int reportId;
    @Getter
    @Setter
    private Date reportDateTime;
    @Getter
    @Setter
    private int flightId;
    @Getter
    @Setter
    private int userId;
    @Getter
    @Setter
    private String reportStatus;
    @Getter
    @Setter
    private List<ReportDetail> reportDetails;

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
