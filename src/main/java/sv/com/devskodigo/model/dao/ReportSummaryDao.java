package sv.com.devskodigo.model.dao;

import sv.com.devskodigo.model.dto.ReportSummaryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportSummaryDao extends DbConnection
        implements IInsert<ReportSummaryDto>, IUpdate<ReportSummaryDto>, IRead<ReportSummaryDto, Integer> {
    private static final String TABLE_NAME = "reportSummary";
    //Column names
    private static final String SUMMARY_ID = "reportSummary_id";
    private static final String SUMMARY_DATETIME = "reportSummary_datetime";
    private static final String FLIGHT_ID = "flight_id";
    private static final String USER_ID = "user_id";
    private static final String STATUS_ID = "status_id";

    @Override
    public List<ReportSummaryDto> getList() {
        Connection conn = null;
        List<ReportSummaryDto> list = new ArrayList<>();
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s".formatted(TABLE_NAME);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new ReportSummaryDto(
                        rs.getInt(SUMMARY_ID),
                        rs.getDate(SUMMARY_DATETIME),
                        rs.getInt(FLIGHT_ID),
                        rs.getInt(USER_ID),
                        rs.getInt(STATUS_ID)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public ReportSummaryDto read(Integer id) {
        Connection conn = null;
        ReportSummaryDto summary = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, SUMMARY_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                summary = new ReportSummaryDto(
                        rs.getInt(SUMMARY_ID),
                        rs.getDate(SUMMARY_DATETIME),
                        rs.getInt(FLIGHT_ID),
                        rs.getInt(USER_ID),
                        rs.getInt(STATUS_ID)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return summary;
    }

    @Override
    public void insert(ReportSummaryDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)"
                    .formatted(TABLE_NAME, SUMMARY_DATETIME, FLIGHT_ID, USER_ID, STATUS_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, new java.sql.Date(t.getReportDateTime().getTime()));
            preparedStmt.setInt(2, t.getFlightId());
            preparedStmt.setInt(3, t.getUserId());
            preparedStmt.setInt(4, t.getReportStatusId());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(ReportSummaryDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?  WHERE %s = ?"
                    .formatted(TABLE_NAME, SUMMARY_DATETIME, FLIGHT_ID, USER_ID, STATUS_ID, SUMMARY_ID);

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, new java.sql.Date(t.getReportDateTime().getTime()));
            preparedStmt.setInt(2, t.getFlightId());
            preparedStmt.setInt(3, t.getUserId());
            preparedStmt.setInt(4, t.getReportStatusId());
            preparedStmt.setInt(5, t.getReportId());

            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
