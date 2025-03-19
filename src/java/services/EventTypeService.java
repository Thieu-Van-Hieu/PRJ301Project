/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entity.EventType;
import repository.EventTypeRepository;
import factory.EventTypeFactory;
import java.util.ArrayList;
/**
 *
 * @author hunggt1572004
 */
public class EventTypeService {
    private final EventTypeRepository eventTypeRepository;
    
    public EventTypeService(){
        this.eventTypeRepository = EventTypeFactory.getEventTypeRepository();
    }
    
    public ArrayList<EventType> selectAllEventType(){
        return eventTypeRepository.getAllEventType();
    }
}
