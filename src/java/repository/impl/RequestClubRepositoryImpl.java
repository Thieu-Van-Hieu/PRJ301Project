package repository.impl;

import java.sql.*;
import java.util.ArrayList;

import dto.RequestClubResponse;
import repository.RequestClubRepository;
import util.DBContext;

public class RequestClubRepositoryImpl implements RequestClubRepository {

    @Override
    public ArrayList<RequestClubResponse> getAllRequest(int clubId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    select cjr.id, cjr.clubId, cjr.userId, ui.firstName, ui.studentId
                    from club_join_requests as cjr
                    join user_informations as ui on cjr.id = ui.userId
                    where cjr.clubId = ? and cjr.status != N'Chấp nhận'
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            ResultSet rs = statement.executeQuery();
            ArrayList<RequestClubResponse> requests = new ArrayList<>();
            while (rs.next()) {
                RequestClubResponse request = new RequestClubResponse();
                request.setId(rs.getInt("id"));
                request.setClubId(rs.getString("clubId"));
                request.setName(rs.getString("firstName"));
                request.setStudentId(rs.getString("studentId"));
                requests.add(request);
            }
            return requests;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            } else {
                System.out.println("Connection is not null");
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RequestClubResponse getRequest(int id) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    select *
                    from club_join_requests
                    where id = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                RequestClubResponse request = new RequestClubResponse();
                request.setId(rs.getInt("id"));
                request.setClubId(rs.getString("club_id"));
                request.setName(rs.getString("name"));
                request.setStudentId(rs.getString("student_id"));
                request.setStatus(rs.getString("status"));
                return request;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateRequest(int id, String status) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    update club_join_requests
                    set status = ?
                    where id = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
