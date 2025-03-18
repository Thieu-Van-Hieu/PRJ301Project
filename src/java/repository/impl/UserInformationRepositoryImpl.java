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
                         SELECT TOP (1000) [id]
                               ,[userId]
                               ,[firstName]
                               ,[lastName]
                               ,[email]
                               ,[studentId]
                               ,[address]
                               ,[gender]
                               ,[birthday]
                           FROM [PRJ301Project].[dbo].[user_informations]
                           where email = ?
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
            if(rs == 0){
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
                         SELECT TOP (1000) [id]
                               ,[userId]
                               ,[firstName]
                               ,[lastName]
                               ,[email]
                               ,[studentId]
                               ,[address]
                               ,[gender]
                               ,[birthday]
                           FROM [PRJ301Project].[dbo].[user_informations]
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
}
