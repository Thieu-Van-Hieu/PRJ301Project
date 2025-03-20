/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import entity.Club;
import java.util.ArrayList;
import repository.ClubRepository;
import util.DBContext;
import java.sql.*;

/**
 *
 * @author hunggt1572004
 */
public class ClubRepositoryImpl implements ClubRepository{

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
            
            while(rs.next()){
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
        } catch (Exception e){
            e.printStackTrace();
        }
        return clubs;
    }
    
}
