package sv.com.devskodigo.model.dao;

import sv.com.devskodigo.model.dto.AirlineDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirlineDao extends DbConnection
        implements IInsert<AirlineDto>,IUpdate<AirlineDto>,IRead<AirlineDto,Integer>, IDelete<Integer>{
    private static final String TABLE_NAME = "airline";
    //Column names
    private static final String AIRLINE_ID = "airline_id";
    private static final String AIRLINE_NAME = "airline_name";

    public List<AirlineDto> list() {
        Connection conn = null;
        List<AirlineDto> list = new ArrayList<>();
        try{
            conn = getConnection();
            String query = "SELECT * FROM %s".formatted(TABLE_NAME);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new AirlineDto(
                        rs.getInt(AIRLINE_ID),
                        rs.getString(AIRLINE_NAME)
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
    public List<AirlineDto> getList() {
        return null;
    }

    public AirlineDto read(Integer id) {
        Connection conn = null;
        AirlineDto airline = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME,AIRLINE_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                airline = new AirlineDto(
                        rs.getInt(AIRLINE_ID),
                        rs.getString(AIRLINE_NAME)
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
        return airline;
    }

    public void insert(AirlineDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s) VALUES (?)".formatted(TABLE_NAME,AIRLINE_NAME);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,t.getAirlineName());
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

    public void update(AirlineDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE %s SET %s = ?  WHERE %s = ?"
                    .formatted(TABLE_NAME,AIRLINE_NAME, AIRLINE_ID);

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getAirlineName());
            preparedStmt.setInt(2, t.getAirlineId());

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


    @Override
    public void delete(Integer idTarget) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "DELETE FROM %s WHERE %s = ?"
                    .formatted(TABLE_NAME, AIRLINE_ID);

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, idTarget);

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
