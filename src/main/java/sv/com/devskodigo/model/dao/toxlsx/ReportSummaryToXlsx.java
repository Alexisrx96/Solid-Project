package sv.com.devskodigo.model.dao.toxlsx;

import sv.com.devskodigo.DataOperations;
import sv.com.devskodigo.model.dto.ReportSummaryDto;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReportSummaryToXlsx implements DataOperations<ReportSummaryDto>{
    private final String filePath = "ReportDetails.xlsx";
    private Map<String, Object[]> data;
    private Iterator rowIterator;
    private final Object[] header = new Object[]{"ID", "DateTime", "Classification", "Description", "Report"};
    @Override
    public void readDataset() {

    }

    @Override
    public void addData(ReportSummaryDto reportSummaryDto) {

    }

    @Override
    public void updateData(ReportSummaryDto reportSummaryDto) {

    }

    @Override
    public void searchData(int id) {

    }

    @Override
    public void deleteData(int id) {

    }

    @Override
    public List<ReportSummaryDto> dataList() {
        return null;
    }
}
