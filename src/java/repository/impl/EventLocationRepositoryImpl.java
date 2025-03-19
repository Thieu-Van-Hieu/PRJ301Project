/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import repository.EventLocationRepository;
import util.DBContext;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author hunggt1572004
 */
public class EventLocationRepositoryImpl implements EventLocationRepository{

    @Override
    public void addEventLocation(String province, String district, String ward, String address) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         insert into event_locations(province, district, ward, address)
                         values (?, ?, ?, ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setNString(1, province);
            statement.setNString(2, district);
            statement.setNString(3, ward);
            statement.setNString(4, address);
            
            rs = statement.executeUpdate();
            if(rs == 0){
                throw new Exception();
            }            
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getEventLocationId(String province, String district, String ward, String address) {
        DBContext db = DBContext.getInstance();
        int id = -1;
        try {
            String sql = """
                         select [id] from event_locations 
                         where province = ? and district = ? and ward = ? and address = ?
                         order by [id] desc;
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, province);
            statement.setString(2, district);
            statement.setString(3, ward);
            statement.setString(4, address);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                return rs.getInt("id");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return id;
    }
    
}
