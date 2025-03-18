/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import factory.EventFactory;
import java.sql.Timestamp;
import repository.EventRepository;
import dto.EventResponse;
import java.util.ArrayList;
/**
 *
 * @author hunggt1572004
 */
public class EventService {
    private final EventRepository eventRepository;
    
    public EventService(){
        this.eventRepository = EventFactory.getEventRepository();
    }
    
    public void addEvent(int clubId, int userId, String name, String description, int typeId, Timestamp startDate, Timestamp endDate, int locationId, String img){
        eventRepository.addEvent(clubId, userId, name, description, typeId, startDate, endDate, locationId, img);        
    }
    
    public ArrayList<EventResponse> getAllEventDescription(){
        return eventRepository.getEventDescription();
    }
}
