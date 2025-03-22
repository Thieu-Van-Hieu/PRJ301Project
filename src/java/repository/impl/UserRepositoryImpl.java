/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

/**
 *
 * @author ngoct
 */
import util.DBContext;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import repository.UserRepository;
import entity.User;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean isLogin(String username, String password) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                     SELECT * FROM users u
                     WHERE u.username = ? AND u.password = ?
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getUsername(String username) {
        DBContext db = DBContext.getInstance();
        User user = new User();
        try {
            String sql = """
                           select * from users
                           where username = ?
                             """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUserName(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setRole("role")
                        .build();
            }
            return user;
        } catch (Exception e) {
            return new User();
        }
    }

    @Override
    public void addUserRoleMember(String username, String password) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                          insert into users values(?, ?, ?)
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, "member");
            rs = st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public int getUserId(String username) {
        DBContext db = DBContext.getInstance();
        int result = 0;
        try {
            String sql = """
                       select * from users
                       where username = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("id");
            }
            return result;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void resetPassword(String username, String password) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                          update users
                          set password = ?
                          where username = ?
                         """;
            PreparedStatement st = db.getConnection().prepareCall(sql);
            st.setString(1, password);
            st.setString(2, username);
            rs = st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

}
