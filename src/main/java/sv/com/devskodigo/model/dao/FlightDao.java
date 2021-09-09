package sv.com.devskodigo.model.dao;

import sv.com.devskodigo.model.dto.FlightDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDao extends DbConnection implements IInsert<FlightDto>,IUpdate<FlightDto>,IRead<FlightDto,Integer>{
    private static final String TABLE_NAME = "flight";
    //Column names
    private static final String FLIGHT_ID = "flight_id";
    private static final String FLIGHT_DATETIME_DEPART = "flight_datetime_depart";
    private static final String FLIGHT_DATETIME_ARRIVAL = "flight_datetime_arrival";
    private static final String FLIGHT_WEATHER_REPORT = "flight_weather_report";
    private static final String FLIGHT_COUNTRY_DEPART_ID = "flight_country_depart_id";
    private static final String FLIGHT_CITY_DEPART_ID = "flight_city_depart_id";
    private static final String FLIGHT_CITY_ARRIVAL_ID = "flight_city_arrival_id";
    private static final String FLIGHT_COUNTRY_ARRIVAL_ID = "flight_country_arrival_id";
    private static final String STATUS_ID = "status_id";
    private static final String AIRCRAFT_ID = "aircraft_id";
    private static final String AIRLINE_ID = "airline_id";
    private static final String USER_ID = "user_id";


    public List<FlightDto> list() {
        Connection conn = null;
        List<FlightDto> list = new ArrayList<>();
        try{
            conn = getConnection();
            String query = "SELECT * FROM %s".formatted(TABLE_NAME);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new FlightDto(
                        rs.getInt(FLIGHT_ID),
                        rs.getDate(FLIGHT_DATETIME_DEPART),
                        rs.getDate(FLIGHT_DATETIME_ARRIVAL),
                        rs.getString(FLIGHT_WEATHER_REPORT),
                        rs.getInt(FLIGHT_COUNTRY_DEPART_ID),
                        rs.getInt(FLIGHT_CITY_DEPART_ID),
                        rs.getInt(FLIGHT_CITY_ARRIVAL_ID),
                        rs.getInt(FLIGHT_COUNTRY_ARRIVAL_ID),
                        rs.getInt(STATUS_ID),
                        rs.getInt(AIRCRAFT_ID),
                        rs.getInt(AIRLINE_ID),
                        rs.getInt(USER_ID)
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
    public List<FlightDto> getList() {
        return null;
    }

    public FlightDto read(Integer id) {
        Connection conn = null;
        FlightDto flight = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, USER_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                flight = new FlightDto(
                        rs.getInt(FLIGHT_ID),
                        rs.getDate(FLIGHT_DATETIME_DEPART),
                        rs.getDate(FLIGHT_DATETIME_ARRIVAL),
                        rs.getString(FLIGHT_WEATHER_REPORT),
                        rs.getInt(FLIGHT_COUNTRY_DEPART_ID),
                        rs.getInt(FLIGHT_CITY_DEPART_ID),
                        rs.getInt(FLIGHT_CITY_ARRIVAL_ID),
                        rs.getInt(FLIGHT_COUNTRY_ARRIVAL_ID),
                        rs.getInt(STATUS_ID),
                        rs.getInt(AIRCRAFT_ID),
                        rs.getInt(AIRLINE_ID),
                        rs.getInt(USER_ID)
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
        return flight;
    }

    public void insert(FlightDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?,?,?,?,?,?)".formatted(TABLE_NAME,FLIGHT_DATETIME_DEPART, FLIGHT_DATETIME_ARRIVAL, FLIGHT_WEATHER_REPORT, FLIGHT_COUNTRY_DEPART_ID, FLIGHT_CITY_DEPART_ID, FLIGHT_CITY_ARRIVAL_ID, FLIGHT_COUNTRY_ARRIVAL_ID, STATUS_ID, AIRCRAFT_ID, AIRLINE_ID, USER_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, (Date) t.getFlightDateTimeDep());
            preparedStmt.setDate(2, (Date) t.getFlightDateTimeArr());
            preparedStmt.setString(3,t.getFlightWeatherRpt());
            preparedStmt.setInt(4,t.getFlightCountryIdDep());
            preparedStmt.setInt(5,t.getFlightCityIdDep());
            preparedStmt.setInt(6,t.getFlightCityIdArr());
            preparedStmt.setInt(7,t.getFlightCountryIdArr());
            preparedStmt.setInt(8,t.getFlightStatus());
            preparedStmt.setInt(9,t.getAircfratId());
            preparedStmt.setInt(10,t.getAirlineId());
            preparedStmt.setInt(11,t.getUserId());


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

    public void update(FlightDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE %s SET %s = ?,%s = ?, %s = ?, %s = ?, %s = ?,%s = ?,%s = ?,%s = ?,%s = ?,%s = ?,%s = ? WHERE %s = ?"
                    .formatted(TABLE_NAME, FLIGHT_DATETIME_DEPART, FLIGHT_DATETIME_ARRIVAL, FLIGHT_WEATHER_REPORT, FLIGHT_COUNTRY_DEPART_ID, FLIGHT_CITY_DEPART_ID, FLIGHT_CITY_ARRIVAL_ID, FLIGHT_COUNTRY_ARRIVAL_ID, STATUS_ID, AIRCRAFT_ID, AIRLINE_ID, USER_ID, FLIGHT_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, (Date) t.getFlightDateTimeDep());
            preparedStmt.setDate(2, (Date) t.getFlightDateTimeArr());
            preparedStmt.setString(3,t.getFlightWeatherRpt());
            preparedStmt.setInt(4,t.getFlightCountryIdDep());
            preparedStmt.setInt(5,t.getFlightCityIdDep());
            preparedStmt.setInt(6,t.getFlightCityIdArr());
            preparedStmt.setInt(7,t.getFlightCountryIdArr());
            preparedStmt.setInt(8,t.getFlightStatus());
            preparedStmt.setInt(9,t.getAircfratId());
            preparedStmt.setInt(10,t.getAirlineId());
            preparedStmt.setInt(11,t.getUserId());
            preparedStmt.setInt(12,t.getFlightId());

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
