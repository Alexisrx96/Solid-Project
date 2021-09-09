package sv.com.devskodigo.model.dao;

import sv.com.devskodigo.model.dto.ReportDetailDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDetailDao extends DbConnection
        implements IInsert<ReportDetailDto>, IUpdate<ReportDetailDto>, IRead<ReportDetailDto, Integer> {
    private static final String TABLE_NAME = "reportDetails";
    //Column names
    private static final String DETAILS_ID = "reportDetails_id";
    private static final String DETAILS_DATETIME = "reportDetails_datetime";
    private static final String DETAILS_DESCRIPTION = "reportDetails_description";
    private static final String DETAILS_CLASIFICATION = "reportDetails_clasification";
    private static final String SUMMARY_ID = "reportSummary_id";

    public List<ReportDetailDto> getList() {
        Connection conn = null;
        List<ReportDetailDto> list = new ArrayList<>();
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s".formatted(TABLE_NAME);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new ReportDetailDto(
                        rs.getInt(DETAILS_ID),
                        rs.getDate(DETAILS_DATETIME),
                        rs.getString(DETAILS_CLASIFICATION),
                        rs.getString(DETAILS_CLASIFICATION),
                        rs.getInt(SUMMARY_ID)
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

    public ReportDetailDto read(Integer id) {
        Connection conn = null;
        ReportDetailDto detail = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, DETAILS_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                detail = new ReportDetailDto(
                        rs.getInt(DETAILS_ID),
                        rs.getDate(DETAILS_DATETIME),
                        rs.getString(DETAILS_CLASIFICATION),
                        rs.getString(DETAILS_CLASIFICATION),
                        rs.getInt(SUMMARY_ID)
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
        return detail;
    }

    public void insert(ReportDetailDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)"
                    .formatted(TABLE_NAME, DETAILS_DATETIME, DETAILS_DESCRIPTION, DETAILS_CLASIFICATION, SUMMARY_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, new java.sql.Date(t.getDetailDateTime().getTime()));
            preparedStmt.setString(2, t.getDetailDescription());
            preparedStmt.setString(3, t.getDetailClassification());
            preparedStmt.setInt(4, t.getReportSummaryId());
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

    public void update(ReportDetailDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?  WHERE %s = ?"
                    .formatted(TABLE_NAME, DETAILS_DATETIME, DETAILS_DESCRIPTION, DETAILS_CLASIFICATION, SUMMARY_ID, DETAILS_ID);

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, new java.sql.Date(t.getDetailDateTime().getTime()));
            preparedStmt.setString(2, t.getDetailDescription());
            preparedStmt.setString(3, t.getDetailClassification());
            preparedStmt.setInt(4, t.getReportSummaryId());
            preparedStmt.setInt(5, t.getDetailId());

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
