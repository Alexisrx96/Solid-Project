package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReportSummaryDto {
    public ReportSummaryDto(int reportId, Date reportDateTime, int flight, int user, String reportStatus) {
        this.reportId = reportId;
        this.reportDateTime = reportDateTime;
        this.flight = flight;
        this.user = user;
        this.reportStatus = reportStatus;
    }
    public ReportSummaryDto() {
    }

    private int reportId;
    private Date reportDateTime;
    private int flight;
    private int user;
    private String reportStatus;
    //Optional
    //private List<ReportDetailDto> reportDetails;
}
