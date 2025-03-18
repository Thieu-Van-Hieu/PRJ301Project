/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.repositoryImpl;

import java.sql.*;
import repository.UserInformationRepository;
import singleton.DBContext;

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
}
