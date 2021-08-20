package sv.com.devskodigo.dto;

import lombok.Getter;
import lombok.Setter;
import sv.com.devskodigo.Flight;

import java.util.Date;
import java.util.List;

public class ReportSummaryDto {
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
    private UserDto user;
    @Getter
    @Setter
    private String reportStatus;
    //Optional
    @Getter
    @Setter
    private List<ReportDetailDto> reportDetails;
}
