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
import java.util.ArrayList;

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

    @Override
    public String getUserRole(int userId) {
        DBContext db = DBContext.getInstance();
        String role = "";
        try {
            String sql = """
                     SELECT * FROM users u
                     WHERE u.id = ?
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                role = rs.getString("role");
            }

        } catch (Exception e) {
            return role;
        }
        return role;
    }

    @Override
    public ArrayList<User> getUserForAdmin() {
        DBContext db = DBContext.getInstance();
        ArrayList<User> results = new ArrayList<>();
        
        try {
            String sql = """
                         SELECT id, username, role FROM users 
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                int userId = rs.getInt("id");
                String username = rs.getString("username");
                String role = rs.getString("role");
                
                User user = new User.Builder().setId(userId).setUserName(username).setRole(role).build();
                results.add(user);
            }
        } catch (Exception e){
            return new ArrayList<>();
        }
        return results;
    }

    @Override
    public void updateUserRole(int userId, String role) {
        DBContext db = DBContext.getInstance();
        
        try {
            String sql = """
                         UPDATE users 
                         SET role = ? 
                         WHERE id = ?;
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, role);
            st.setInt(2, userId);
            
            
            int rs = st.executeUpdate();
            if(rs == 0 ){
                throw new Exception();
            }
        } catch (Exception e){
            return;
        }
    }

}
