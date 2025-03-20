/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dto.MemberDTO;
import dto.MemberResponse;
import java.util.ArrayList;
import repository.MemberRepository;
import util.DBContext;
import java.sql.*;
/**
 *
 * @author hunggt1572004
 */
public class MemberRepositoryImpl implements MemberRepository{

    @Override
    public ArrayList<MemberResponse> getAllMemberOfClub(int clubId) {
        DBContext db = DBContext.getInstance();
        ArrayList<MemberResponse> memberResponses = new ArrayList<>();
        
        try {
            String sql = """
                         select 
                         ui.studentId,
                         ui.lastName,
                         ui.firstName,
                         ui.birthday,
                         ui.gender,
                         d.name AS departmentName,
                         m.role
                         from members m
                         JOIN user_informations AS ui ON ui.userId = m.userId
                         JOIN departments AS d ON d.id = m.deptId
                         WHERE m.clubId = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                String rollNumber = rs.getString("studentId");
                String lastName = rs.getNString("lastName");
                String firstName = rs.getNString("firstName");
                String birthday = rs.getString("birthday");
                String gender = rs.getNString("gender");
                String delName = rs.getNString("departmentName");
                String role = rs.getNString("role");
                
                MemberResponse memberResponse = new MemberResponse(rollNumber, lastName, firstName, birthday, gender, delName, role);
                memberResponses.add(memberResponse);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return memberResponses;
    }
    
     

    @Override
    public void deleteMemberOfClub(MemberDTO memberDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
