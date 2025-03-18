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
import model.User;

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
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUsername(String username) {
        DBContext db = DBContext.getInstance();
        User user = null;
        try {
            String sql = """
                         SELECT TOP (1000) [id]
                               ,[username]
                               ,[password]
                               ,[role]
                           FROM [PRJ301Project].[dbo].[users]
                           where username = ?
                             """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .id(rs.getInt("id"))
                        .userName(rs.getString("username"))
                        .password(rs.getString("password")) 
                        .role("role")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

}
