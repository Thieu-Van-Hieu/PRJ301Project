/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.sql.Timestamp;
import java.util.*;
import dto.EventResponse;
/**
 *
 * @author hunggt1572004
 */
public interface EventRepository {
    public void addEvent(int clubId, int userId, String name, String description, int typeId, Timestamp startDate, Timestamp endDate, int locationId, String img);
    
    public ArrayList<EventResponse> getEventDescription();
}
