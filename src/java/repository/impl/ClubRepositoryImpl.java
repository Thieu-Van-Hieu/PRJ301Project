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
                         where m.role = 'Ch? Nhi?m'
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
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
}
