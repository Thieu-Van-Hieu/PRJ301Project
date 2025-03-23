/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import entity.Department;
import repository.DepartmentRepository;
import java.sql.*;
import util.DBContext;

/**
 *
 * @author ngoct
 */
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Override
    public Department getDepartmentByMemberId(int MemberID) {
        DBContext db = DBContext.getInstance();
        Department results = new Department();
        try {
            String sql = """
                         select d.* from departments d 
                         	 join members m on m.deptId = d.id
                         	 where m.id = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, MemberID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                results = new Department(rs.getInt("id"), rs.getString("name"));
            }
            return results;
        } catch (Exception e) {
            return new Department();
        }
    }

}
