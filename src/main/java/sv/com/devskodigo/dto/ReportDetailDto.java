package sv.com.devskodigo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ReportDetailDto {
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
    @Getter
    @Setter
    private ReportSummaryDto reportSummary;
}
