/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.impl.EventTypeRepositoryImpl;
import repository.EventTypeRepository;

/**
 *
 * @author hunggt1572004
 */
public class EventTypeFactory {
    private static final EventTypeRepository eventTypeRepository = new EventTypeRepositoryImpl();

    public static EventTypeRepository getEventTypeRepository() {
        return eventTypeRepository;
    } 
    
}
