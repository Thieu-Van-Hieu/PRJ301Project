/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.EventRepository;
import repository.impl.EventRepositoryImpl;

/**
 *
 * @author hunggt1572004
 */
public class EventFactory {
    
    private static final EventRepository eventRepository = new EventRepositoryImpl();
    
    public static EventRepository getEventRepository(){
        return eventRepository;
    }
}
