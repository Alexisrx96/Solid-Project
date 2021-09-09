package sv.com.devskodigo.model.dao;


import sv.com.devskodigo.model.dto.AircraftDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircraftDao extends DbConnection implements IInsert<AircraftDto>,IUpdate<AircraftDto>,IRead<AircraftDto,Integer>{

    private static final String TABLE_NAME = "aircraft";
    //Column names
    private static final String AIRCRAFT_ID = "aircraft_id";
    private static final String AIRCRAFT_REGISTRATION = "aircraft_registration";
    private static final String AIRCRAFT_MODEL = "aircraft_model";
    private static final String AIRCRAFT_PASSENGER = "aircraft_passenger";
    private static final String AIRCRAFT_FUEL_RANGE = "aircraft_fuel_range";
    private static final String AIRCRAFT_STATUS = "status_id";

    public List<AircraftDto> list() {
        Connection conn = null;
        List<AircraftDto> list = new ArrayList<>();
        try{
            conn = getConnection();
            String query = "SELECT * FROM %s".formatted(TABLE_NAME);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new AircraftDto(
                        rs.getInt(AIRCRAFT_ID),
                        rs.getString(AIRCRAFT_REGISTRATION),
                        rs.getString(AIRCRAFT_MODEL),
                        rs.getInt(AIRCRAFT_PASSENGER),
                        (float ) rs.getDouble(AIRCRAFT_FUEL_RANGE),
                        rs.getInt(AIRCRAFT_STATUS)
                ));
            }
        }catch (SQLException e) {
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
    public List<AircraftDto> getList() {
        return null;
    }

    public AircraftDto read(Integer id) {
        Connection conn = null;
        AircraftDto aircraft = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME,AIRCRAFT_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                aircraft = new AircraftDto(
                        rs.getInt(AIRCRAFT_ID),
                        rs.getString(AIRCRAFT_REGISTRATION),
                        rs.getString(AIRCRAFT_MODEL),
                        rs.getInt(AIRCRAFT_PASSENGER),
                        (float ) rs.getDouble(AIRCRAFT_FUEL_RANGE),
                        rs.getInt(AIRCRAFT_STATUS)
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
        return aircraft;
    }

    public void insert(AircraftDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)".formatted(TABLE_NAME,AIRCRAFT_REGISTRATION, AIRCRAFT_MODEL, AIRCRAFT_PASSENGER, AIRCRAFT_FUEL_RANGE, AIRCRAFT_STATUS);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1,t.getAircraftRegistration());
                preparedStmt.setString(2,t.getAircraftModel());
                preparedStmt.setInt(3, t.getAircraftPassengersCapacity());
                preparedStmt.setDouble(4, t.getAircraftMaxFuel());
                preparedStmt.setInt(5, t.getAircraftStatus());
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

    public void update(AircraftDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE %s SET %s = ?,%s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?"
                    .formatted(TABLE_NAME,AIRCRAFT_REGISTRATION, AIRCRAFT_MODEL, AIRCRAFT_PASSENGER, AIRCRAFT_FUEL_RANGE, AIRCRAFT_STATUS, AIRCRAFT_ID);

            PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1,t.getAircraftRegistration());
                preparedStmt.setString(2,t.getAircraftModel());
                preparedStmt.setInt(3, t.getAircraftPassengersCapacity());
                preparedStmt.setDouble(4, t.getAircraftMaxFuel());
                preparedStmt.setInt(5, t.getAircraftStatus());
                preparedStmt.setInt(6, t.getAircraftId());
                preparedStmt.execute();
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
