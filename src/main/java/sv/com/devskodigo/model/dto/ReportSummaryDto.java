package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReportSummaryDto {
    private int reportId;
    private Date reportDateTime;
    private int flightId;
    private int userId;
    private int reportStatusId;

    public ReportSummaryDto(int reportId, Date reportDateTime, int flightId, int userId, int reportStatusId) {
        this.reportId = reportId;
        this.reportDateTime = reportDateTime;
        this.flightId = flightId;
        this.userId = userId;
        this.reportStatusId = reportStatusId;
    }

    public ReportSummaryDto() {
    }
    //Optional
    //private List<ReportDetailDto> reportDetails;
}
