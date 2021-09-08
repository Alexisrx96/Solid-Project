package sv.com.devskodigo.model.dao;

import sv.com.devskodigo.model.dto.CityDto;
import sv.com.devskodigo.model.dto.ReportSummaryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportSummaryDao  extends DbConnection {
    private static final String TABLE_NAME = "reportSummary";
    //Column names
    private static final String SUMMARY_ID = "reportSummary_id";
    private static final String SUMMARY_DATETIME = "reportSummary_datetime";
    private static final String FLIGHT_ID = "flight_id";
    private static final String USER_ID = "user_id";
    private static final String STATUS_ID = "status_id";

    public List<ReportSummaryDto> list() {
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
                        rs.getString(STATUS_ID)
                ));
            }
        } catch ( SQLException e) {
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

    public ReportSummaryDto read(Integer id) {
        Connection conn = null;
        ReportSummaryDto city = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, SUMMARY_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                city = new ReportSummaryDto(
                        rs.getInt(SUMMARY_ID),
                        rs.getDate(SUMMARY_DATETIME),
                        rs.getInt(FLIGHT_ID),
                        rs.getInt(USER_ID),
                        rs.getString(STATUS_ID)
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
        return city;
    }

    public void insert(ReportSummaryDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)".formatted(TABLE_NAME, SUMMARY_DATETIME, FLIGHT_ID, USER_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
           /* preparedStmt.setString(1,t.getCityName());
            preparedStmt.setFloat(2,t.getCityCoords());
            preparedStmt.setInt(3,t.getCountryId());*/
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
            String query = "UPDATE %s SET %s = ?, %s = ?, %s = ?  WHERE %s = ?"
                    .formatted(TABLE_NAME, SUMMARY_DATETIME, FLIGHT_ID, USER_ID, SUMMARY_ID);

            PreparedStatement preparedStmt = conn.prepareStatement(query);
           /* preparedStmt.setString(1, t.getCityName());
            preparedStmt.setFloat(2, t.getCityCoords());
            preparedStmt.setInt(3, t.getCountryId());
            preparedStmt.setInt(4, t.getCityId());*/

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
