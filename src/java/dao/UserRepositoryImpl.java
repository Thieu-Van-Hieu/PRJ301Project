/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ngoct
 */
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UserRepositoryImpl {

    public static boolean isLogin(String username, String password) {
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

}
