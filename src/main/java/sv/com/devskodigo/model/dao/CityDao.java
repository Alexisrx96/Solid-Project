package sv.com.devskodigo.model.dao;

import sv.com.devskodigo.model.dto.CityDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao extends DbConnection
        implements IInsert<CityDto>,IUpdate<CityDto>,IRead<CityDto,Integer>{
    private static final String TABLE_NAME = "city";
    //Column names
    private static final String CITY_ID = "city_id";
    private static final String CITY_NAME = "city_name";
    private static final String CITY_COORDINATES = "city_coordinates";
    private static final String COUNTRY_ID = "country_id";

    public List<CityDto> getList() {
        Connection conn = null;
        List<CityDto> list = new ArrayList<>();
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s".formatted(TABLE_NAME);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new CityDto(
                        rs.getInt(CITY_ID),
                        rs.getString(CITY_NAME),
                        rs.getFloat(CITY_COORDINATES),
                        rs.getInt(COUNTRY_ID)
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

    public CityDto read(Integer id) {
        Connection conn = null;
        CityDto city = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, CITY_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                city = new CityDto(
                        rs.getInt(CITY_ID),
                        rs.getString(CITY_NAME),
                        rs.getFloat(CITY_COORDINATES),
                        rs.getInt(COUNTRY_ID)
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

    public void insert(CityDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)".formatted(TABLE_NAME, CITY_NAME, CITY_COORDINATES, COUNTRY_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getCityName());
            preparedStmt.setFloat(2, t.getCityCoords());
            preparedStmt.setInt(3, t.getCountryId());
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

    public void update(CityDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE %s SET %s = ?, %s = ?, %s = ?  WHERE %s = ?"
                    .formatted(TABLE_NAME, CITY_NAME, CITY_COORDINATES, COUNTRY_ID, CITY_ID);

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getCityName());
            preparedStmt.setFloat(2, t.getCityCoords());
            preparedStmt.setInt(3, t.getCountryId());
            preparedStmt.setInt(4, t.getCityId());

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
