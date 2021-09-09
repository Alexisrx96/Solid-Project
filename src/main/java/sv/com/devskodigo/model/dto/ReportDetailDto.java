package sv.com.devskodigo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReportDetailDto {
    private int detailId;
    private Date detailDateTime;
    private String detailClassification;
    private String detailDescription;
    private int reportSummaryId;
    public ReportDetailDto(int detailId, Date detailDateTime, String detailClassification, String detailDescription, int reportSummaryId) {
        this.detailId = detailId;
        this.detailDateTime = detailDateTime;
        this.detailClassification = detailClassification;
        this.detailDescription = detailDescription;
        this.reportSummaryId = reportSummaryId;
    }
    public ReportDetailDto() {
    }
}
