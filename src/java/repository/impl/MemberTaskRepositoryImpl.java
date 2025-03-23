package repository.impl;

import repository.MemberTaskRepository;
import dto.MemberTaskResponse;
import entity.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DBContext;

public class MemberTaskRepositoryImpl implements MemberTaskRepository {

    private String getMemberRole(int clubId, int memberId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select role
                    from members
                    where clubId = ? and id = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            statement.setInt(2, memberId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
            return null;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null!");
            }
            return null;
        }
    }

    @Override
    public ArrayList<MemberTaskResponse> getMembers(Member member) {
        DBContext db = new DBContext();
        String memberRole = getMemberRole(member.getClubId(), member.getId());
        String memberRoles[] = { "Chủ nhiệm", "Phó chủ nhiệm", "Trưởng nhóm", "Thành viên" };
        String memberRolesAvailable = "";
        int indexOfrole = 0;
        for (int i = 0; i < memberRoles.length; i++) {
            if (memberRole.equals(memberRoles[i])) {
                indexOfrole = i;
                break;
            }
        }
        for (; indexOfrole < memberRoles.length; indexOfrole++) {
            memberRolesAvailable += "role = N'" + memberRoles[indexOfrole] + "'";
            if (indexOfrole != memberRoles.length - 1) {
                memberRolesAvailable += " or ";
            }
        }

        try {

            String sql = """
                    select mb.id, mb.clubId, ui.firstName
                    from members as mb
                    join user_informations as ui on mb.userId = ui.userId
                    where mb.clubId = ? and mb.id != ?
                    """ + "and (" + memberRolesAvailable + ")";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, member.getClubId());
            statement.setInt(2, member.getId());
            ResultSet rs = statement.executeQuery();
            ArrayList<MemberTaskResponse> members = new ArrayList<>();
            while (rs.next()) {
                MemberTaskResponse memberTaskResponse = new MemberTaskResponse();
                memberTaskResponse.setId(rs.getInt("id"));
                memberTaskResponse.setClubId(rs.getInt("clubId"));
                memberTaskResponse.setName(rs.getString("firstName"));
                members.add(memberTaskResponse);
            }

            return members;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null!");
            }

            return null;
        }
    }
}
