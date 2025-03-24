/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dto.ClubResponse;
import entity.Club;
import java.util.ArrayList;
import repository.ClubRepository;
import util.DBContext;
import java.sql.*;

/**
 *
 * @author hunggt1572004
 */
public class ClubRepositoryImpl implements ClubRepository {

    @Override
    public ArrayList<Club> getAllClubIdAndName() {
        DBContext db = DBContext.getInstance();
        ArrayList<Club> clubs = new ArrayList<>();
        try {
            String sql = """
                         select * from Clubs
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Club getIdAndNameEntity = new Club.ClubBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getNString("name"))
                        .setType(rs.getNString("type"))
                        .setDate(rs.getString("createdAt"))
                        .setAvatarClub(rs.getString("avatarClub"))
                        .setCoverImage(rs.getString("coverImage"))
                        .build();
                clubs.add(getIdAndNameEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }

    @Override
    public ArrayList<ClubResponse> getAllClubInformations() {
        DBContext db = DBContext.getInstance();
        ArrayList<ClubResponse> clubResponses = new ArrayList<>();

        try {
            String sql = """
                         select 
                         c.id, c.name, c.description, c.type, c.avatarClub, c.coverImage, (ai.lastName +' ' + ai.firstName) as namePresident
                         from clubs as c
                         join members as m on m.clubId = c.id
                         join user_informations as ai on ai.userId = m.userId
                         where m.role = N'Chủ Nhiệm'
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getNString("name");
                String description = rs.getNString("description");
                String type = rs.getNString("type");
                String avatarClub = rs.getString("avatarClub");
                String coverImage = rs.getString("coverImage");
                String presidentName = rs.getString("namePresident");

                ClubResponse clubResponse = new ClubResponse(id, name, description, type, avatarClub, coverImage, presidentName);
                clubResponses.add(clubResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubResponses;
    }

    @Override
    public ArrayList<ClubResponse> getClubIcons(int userId) {
        DBContext db = DBContext.getInstance();
        ArrayList<ClubResponse> clubResponses = new ArrayList<>();

        try {
            String sql = """
                         select 
                         c.id, c.avatarClub
                         from clubs as c
                         join members as m on m.clubId = c.id
                         where m.userId = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String avatarClub = rs.getString("avatarClub");
                ClubResponse clubResponse = new ClubResponse(id, avatarClub);
                clubResponses.add(clubResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubResponses;
    }

    @Override
    public int addClub(ClubResponse club) {
        DBContext db = DBContext.getInstance();
        int generatedId = -1;
        try {
            String sql = """
                         insert into clubs(name, description, type, createdAt, avatarClub, coverImage)
                         values (?, ?, ?, ?, ?, ?);
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setNString(1, club.getName());
            statement.setNString(2, club.getDescription());
            statement.setNString(3, club.getType());
            statement.setString(4, club.getDate());
            statement.setString(5, club.getAvatarClub());
            statement.setString(6, club.getCoverImage());
            int rs = statement.executeUpdate();

            if (rs > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            return 0;
        }
        return generatedId;
    }

    @Override
    public ArrayList<ClubResponse> getClubsByType(String type) {
        DBContext db = DBContext.getInstance();
        ArrayList<ClubResponse> clubResponses = new ArrayList<>();

        try {
            String sql = """
                         select 
                         c.id, c.name, c.description, c.type, c.avatarClub, c.coverImage, (ai.lastName +' ' + ai.firstName) as namePresident
                         from clubs as c
                         join members as m on m.clubId = c.id
                         join user_informations as ai on ai.userId = m.userId
                         where m.role = N'Chủ Nhiệm' and c.type = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setNString(1, type);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getNString("name");
                String description = rs.getNString("description");
                String avatarClub = rs.getString("avatarClub");
                String coverImage = rs.getString("coverImage");
                String presidentName = rs.getString("namePresident");
                ClubResponse clubResponse = new ClubResponse(id, name, description, type, avatarClub, coverImage, presidentName);
                clubResponses.add(clubResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubResponses;
    }

    @Override
    public ArrayList<Club> getAllClubAdmin() {
        DBContext db = new DBContext();
        ArrayList<Club> results = new ArrayList<>();

        try {
            String sql = """
                         select c.id, c.name, c.type, c.createdAt, concat(ui.lastName, ' ', ui.firstName) as clubChairman from clubs as c
                         join members as m on m.clubId = c.id
                         join user_informations as ui on ui.userId = m.userId
                         where m.role = N'Chủ Nhiệm'
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int clubId = rs.getInt("id");
                String clubName = rs.getNString("name");
                String clubType = rs.getNString("type");
                String createdAt = rs.getString("createdAt");
                String clubChairman = rs.getNString("clubChairman");
                Club club = new Club.ClubBuilder()
                        .setId(clubId)
                        .setName(clubName)
                        .setType(clubType)
                        .setDate(createdAt)
                        .setClubChairman(clubChairman)
                        .build();
                results.add(club);
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return results;
    }

    public void deleteMessageEditLogs(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM message_edit_logs WHERE memberId IN (SELECT id FROM members WHERE clubId = ?)
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteMessages(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM messages WHERE clubId = ?
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteLoves(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM loves WHERE postId IN (SELECT id FROM posts WHERE clubId = ?)
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteComments(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM comments WHERE postId IN (SELECT id FROM posts WHERE clubId = ?)
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deletePosts(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM posts WHERE clubId = ?
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteMessagesByMembers(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM messages WHERE memberId IN (SELECT id FROM members WHERE clubId = ?)
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteEventParticipants(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM event_participants WHERE memberId IN (SELECT id FROM members WHERE clubId = ?)
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteEvents(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM events WHERE clubId = ?
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteTaskAssignees(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM task_assignees WHERE taskId IN (SELECT id FROM tasks WHERE clubId = ?)
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteTasks(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM tasks WHERE clubId = ?
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteClubJoinRequests(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM club_join_requests WHERE clubId = ?
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void deleteMembers(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                     DELETE FROM members WHERE clubId = ?
                     """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void deleteClub(int clubId) {
        DBContext db = new DBContext();
        try {
            deleteMessageEditLogs(clubId);
            deleteMessages(clubId);
            deleteLoves(clubId);
            deleteComments(clubId);
            deletePosts(clubId);
            deleteMessagesByMembers(clubId);
            deleteEventParticipants(clubId);
            deleteEvents(clubId);
            deleteTaskAssignees(clubId);
            deleteTasks(clubId);
            deleteClubJoinRequests(clubId);
            deleteMembers(clubId);
            String sql = """
                         DELETE FROM clubs WHERE id = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, clubId);
            st.executeUpdate();

        } catch (Exception e) {
            return;
        }
    }

}
