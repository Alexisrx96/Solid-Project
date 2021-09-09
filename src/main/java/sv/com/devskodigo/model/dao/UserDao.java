package sv.com.devskodigo.model.dao;

import sv.com.devskodigo.model.dto.UserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DbConnection implements IInsert<UserDto>,IUpdate<UserDto>,IRead<UserDto,Integer>{

    private static final String TABLE_NAME = "user";
    //Column names
    private static final String USER_ID = "user_id";
    private static final String USER_FIRST_NAME = "user_name";
    private static final String USER_LAST_NAME = "user_lastname";
    private static final String USER_ACCOUNT = "user_account";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_STATUS = "status_id";

    public List<UserDto> list() {
        Connection conn = null;
        List<UserDto> list = new ArrayList<>();
        try{
            conn = getConnection();
            String query = "SELECT * FROM %s".formatted(TABLE_NAME);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new UserDto(
                        rs.getInt(USER_ID),
                        rs.getString(USER_FIRST_NAME),
                        rs.getString(USER_LAST_NAME),
                        rs.getString(USER_ACCOUNT),
                        rs.getString(USER_PASSWORD),
                        rs.getInt(USER_STATUS)
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
    public List<UserDto> getList() {
        return null;
    }

    public UserDto read(Integer id) {
        Connection conn = null;
        UserDto user = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM %s WHERE %s = ?".formatted(TABLE_NAME, USER_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                user = new UserDto(
                        rs.getInt(USER_ID),
                        rs.getString(USER_FIRST_NAME),
                        rs.getString(USER_LAST_NAME),
                        rs.getString(USER_ACCOUNT),
                        rs.getString(USER_PASSWORD),
                        rs.getInt(USER_STATUS)
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
        return user;
    }

    public void insert(UserDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)".formatted(TABLE_NAME,USER_FIRST_NAME, USER_LAST_NAME, USER_ACCOUNT, USER_PASSWORD, USER_STATUS);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,t.getUserFirstName());
            preparedStmt.setString(2,t.getUserLastName());
            preparedStmt.setString(3,t.getUserAccountName());
            preparedStmt.setString(4,t.getUserPassword());
            preparedStmt.setInt(5, t.getUserStatus());

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

    public void update(UserDto t) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE %s SET %s = ?,%s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?"
                    .formatted(TABLE_NAME,USER_FIRST_NAME, USER_LAST_NAME, USER_ACCOUNT, USER_PASSWORD, USER_STATUS, USER_ID);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,t.getUserFirstName());
            preparedStmt.setString(2,t.getUserLastName());
            preparedStmt.setString(3,t.getUserAccountName());
            preparedStmt.setString(4,t.getUserPassword());
            preparedStmt.setInt(5, t.getUserStatus());
            preparedStmt.setInt(6, t.getUserId());
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
