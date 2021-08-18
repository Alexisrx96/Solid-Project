package sv.com.devskodigo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class ReportSummary implements DataOperations {

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
    private Flight flight;
    @Getter
    @Setter
    private User user;
    @Getter
    @Setter
    private String reportStatus;
    /*
    //Optional
    @Getter
    @Setter
    private List<ReportDetail> reportDetails;
    */

    public ReportSummary(Flight flight, User user) {
        this.flight = flight;
        this.user = user;
    }

    public ReportSummary() {
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
