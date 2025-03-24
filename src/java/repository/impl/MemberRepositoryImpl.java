/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dto.FilterMemberDTO;
import dto.MemberDTO;
import dto.MemberResponse;
import entity.Member;
import java.util.ArrayList;
import repository.MemberRepository;
import util.DBContext;
import java.sql.*;

/**
 *
 * @author hunggt1572004
 */
public class MemberRepositoryImpl implements MemberRepository {

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

            while (rs.next()) {
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
            return memberResponses;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteMemberOfClub(MemberDTO memberDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Member getMember(int userId, int clubId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         select * from members
                         where userId = ? and clubId = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, clubId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return new Member.Builder().setId(rs.getInt("id")).setClubId(rs.getInt("clubId")).setRole(rs.getNString("role")).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addMember(MemberDTO member) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         insert into members(userId, clubId, deptId, role)
                         values (?, ?, ?, ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, member.getUserId());
            statement.setInt(2, member.getClubId());
            statement.setInt(3, member.getDeptId());
            statement.setNString(4, member.getRole());

            int rs = statement.executeUpdate();
            if (rs == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            return;
        }
    }

    private String whereFilterMemeber(FilterMemberDTO filterMemberDTO) {
        StringBuilder sql = new StringBuilder("where 1 = 1");
        sql.append(" and d.id = " + filterMemberDTO.getDepartmentId());
        sql.append(" and ui.gender = N" + "'" + filterMemberDTO.getGender() + "'");
        if (filterMemberDTO.getNameSearch() != null && !filterMemberDTO.getNameSearch().isEmpty()) {
            sql.append(" and (lower(ui.firstName) like N'%" + filterMemberDTO.getNameSearch() + "%' or lower(ui.lastName) like N'%" + filterMemberDTO.getNameSearch() + "%')");
        }
        if (filterMemberDTO.getAgeFrom() != null) {
            sql.append(" and (\n"
                    + "       case\n"
                    + "         when dateadd(year, datediff(year, ui.birthday, getdate()), ui.birthday) > getdate()\n"
                    + "           then datediff(year, ui.birthday, getdate()) - 1\n"
                    + "         else datediff(year, ui.birthday, getdate())\n"
                    + "       end\n"
                    + "      ) >= " + filterMemberDTO.getAgeFrom());
        }
        if (filterMemberDTO.getAgeTo() != null) {
            sql.append(" and (\n"
                    + "       case\n"
                    + "         when dateadd(year, datediff(year, ui.birthday, getdate()), ui.birthday) > getdate()\n"
                    + "           then datediff(year, ui.birthday, getdate()) - 1\n"
                    + "         else datediff(year, ui.birthday, getdate())\n"
                    + "       end\n"
                    + "      ) <= " + filterMemberDTO.getAgeTo());
        }
        return sql.toString();
    }

    @Override
    public ArrayList<Member> filterMember(FilterMemberDTO filterMemberDTO) {
        DBContext db = DBContext.getInstance();
        ArrayList<Member> results = new ArrayList<>();
        try {
            String sql = """
                         select m.* from members m
                         join users u on u.id = m.userid
                         join user_informations ui on ui.userid = u.id
                         join departments d on d.id = m.deptid
                         """;
            sql += whereFilterMemeber(filterMemberDTO);
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Member member = new Member.Builder().setId(rs.getInt("id"))
                        .setUserId(rs.getInt("userId"))
                        .setDeptId(rs.getInt("deptId"))
                        .setRole(rs.getString("role"))
                        .setClubId(rs.getInt("clubId"))
                        .build();

                results.add(member);
            }
            return results;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
