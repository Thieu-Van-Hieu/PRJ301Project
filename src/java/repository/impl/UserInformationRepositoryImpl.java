/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dto.UserInformationResponse;
import java.sql.*;
import java.util.ArrayList;
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
            return false;
        }
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
            return;
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
            return false;
        }
    }

    @Override
    public String getUsernameByEmail(String email) {
        DBContext db = DBContext.getInstance();
        StringBuilder result = new StringBuilder();
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
                result.append(rs.getString("username"));
            }
            return result.toString();
        } catch (Exception e) {
            return new StringBuilder().toString();
        }
    }

    @Override
    public UserInformationResponse getNameAvatarOfUser(int userId) {
        DBContext db = DBContext.getInstance();
        UserInformationResponse userInformationResponse = new UserInformationResponse();
        try {
            String sql = """
                           select (ui.lastName + ' ' + ui.firstName) as fullName, avatarImg
                           from user_informations ui
                           join users u on u.id = ui.userId
                           where ui.userId = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                userInformationResponse = new UserInformationResponse(rs.getNString("fullName"), rs.getString("avatarImg"));
            }
            return userInformationResponse;
        } catch (Exception e) {
            return new UserInformationResponse();
        }
    }

    @Override
    public UserInformationResponse getUser(int userId) {
        DBContext db = DBContext.getInstance();
        UserInformationResponse results = new UserInformationResponse();
        try {
            String sql = """
                         select * from user_informations as ui
                         join users as u on u.id = ui.userId
                         where u.id = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                results = new UserInformationResponse(userId,
                        rs.getNString("role"),
                        rs.getNString("firstName"),
                        rs.getNString("lastName"),
                        rs.getString("email"),
                        rs.getString("studentId"),
                        rs.getNString("address"),
                        rs.getString("birthday"),
                        rs.getString("avatarImg"));
            }
            return results;
        } catch (Exception e) {
            return new UserInformationResponse();
        }
    }

    @Override
    public UserInformationResponse getUserSetting(int userId) {
        DBContext db = DBContext.getInstance();
        UserInformationResponse userInformationResponse = new UserInformationResponse();
        try {
            String sql = """
                         select * from user_informations as ui
                         join users as u on u.id = ui.userId
                         where u.id = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                userInformationResponse = new UserInformationResponse(userId,
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getNString("firstName"),
                        rs.getNString("lastName"),
                        rs.getString("email"));
            }
            return userInformationResponse;
        } catch (Exception e) {
            return new UserInformationResponse();
        }
    }

    @Override
    public void updateUserAvatar(UserInformationResponse user) {
        DBContext db = DBContext.getInstance();

        try {
            String sql = """
                         update user_informations 
                         set avatarImg = ?
                         where userId = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, user.getAvatar());
            statement.setInt(2, user.getUserId());
            int rs = statement.executeUpdate();
            if (rs == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            return;
        }
    }

}
