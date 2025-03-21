package repository.impl;

import repository.MemberTaskRepository;
import dto.MemberTaskResponse;
import entity.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DBContext;

public class MemberTaskRepositoryImpl implements MemberTaskRepository {

    @Override
    public ArrayList<MemberTaskResponse> getMembers(Member member) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select mb.userId, mb.clubId, ui.firstName
                    from members as mb
                    join user_informations as ui on mb.userId = ui.userId
                    where mb.clubId = ? and role = N'Thành viên'
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, member.getClubId());
            ResultSet rs = statement.executeQuery();
            ArrayList<MemberTaskResponse> members = new ArrayList<>();
            while (rs.next()) {
                MemberTaskResponse memberTaskResponse = new MemberTaskResponse();
                memberTaskResponse.setId(rs.getInt("userId"));
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
