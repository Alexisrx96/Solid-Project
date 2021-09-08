package sv.com.devskodigo.model.dao;

import sv.com.devskodigo.model.dto.CountryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDao  extends DbConnection {
    private static final String TABLE_NAME = "country";
    //Column names
    private static final String COUNTRY_ID = "country_id";
    private static final String COUNTRY_NAME = "country_name";
    private static final String COUNTRY_COORDINATES = "country_coordinates";

    public List<CountryDto> list() {
        Connection conn = null;
        List<CountryDto> list = new ArrayList<>();
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s".formatted(TABLE_NAME);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new CountryDto(
                        rs.getInt(COUNTRY_ID),
                        rs.getString(COUNTRY_NAME),
                        rs.getFloat(COUNTRY_COORDINATES)
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

    public CountryDto read(Integer id) {
        Connection conn = null;
        CountryDto country = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME,COUNTRY_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                country = new CountryDto(
                        rs.getInt(COUNTRY_ID),
                        rs.getString(COUNTRY_NAME),
                        rs.getFloat(COUNTRY_COORDINATES)
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
        return country;
    }

    public void insert(CountryDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s,%s) VALUES (?,?)".formatted(TABLE_NAME,COUNTRY_NAME, COUNTRY_COORDINATES);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,t.getCountryName());
            preparedStmt.setFloat(2,t.getCountryCoords());
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

    public void update(CountryDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE %s SET %s = ?, %s = ?  WHERE %s = ?"
                    .formatted(TABLE_NAME,COUNTRY_NAME, COUNTRY_COORDINATES, COUNTRY_ID);

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getCountryName());
            preparedStmt.setFloat(2, t.getCountryCoords());
            preparedStmt.setInt(3, t.getCountryId());

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
