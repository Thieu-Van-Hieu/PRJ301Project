package repository.impl;


import entity.EventType;
import java.util.ArrayList;
import repository.EventTypeRepository;
import util.DBContext;
import java.sql.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hunggt1572004
 */
public class EventTypeRepositoryImpl implements EventTypeRepository{

    @Override
    public ArrayList<EventType> getAllEventType() {
        DBContext db = DBContext.getInstance();
        ArrayList<EventType> eventTypes = new ArrayList<>();
        
        try {
            String sql = """
                         select * from type_events
                         """;
            
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                EventType eventType = new EventType(rs.getInt("id"), rs.getString("name"));
                eventTypes.add(eventType);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return eventTypes;
    }
    
}
