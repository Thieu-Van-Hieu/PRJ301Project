/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dto.UserInformationResponse;
import java.sql.*;
import repository.UserInformationRepository;
import util.DBContext;

/**
 *
 * @author ngoct
 */
public class UserInformationRepositoryImpl implements UserInformationRepository {

    @Override
    public boolean isExistEmail(String email) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                           select * from user_informations
                           where email like ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addInformationOfUser(int userid, UserInformationResponse user) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                        insert into user_informations values(?, ?, ?, ?,?, ?, ?, ?)
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, userid);
            st.setString(2, user.getFirstName());
            st.setString(3, user.getLastName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getStudentId());
            st.setString(6, user.getAddress());
            st.setString(7, user.getGender());
            st.setString(8, user.getBirthday());
            rs = st.executeUpdate();
            if (rs == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExistStudentId(String studentId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                          select * from user_informations          
                         where studentId = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, studentId);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getUsernameByEmail(String email) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                           select * from user_informations ui
                           join users u on u.id = ui.userId
                           where ui.email = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getNameOfUser(int userId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                           select (ui.lastName + ' ' + ui.firstName) as fullName from user_informations ui
                           join users u on u.id = ui.userId
                           where ui.userId = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("fullName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
