/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.EventLocationRepository;
import repository.impl.EventLocationRepositoryImpl;

/**
 *
 * @author hunggt1572004
 */
public class EventLocationFactory {
    private static final EventLocationRepository eventLocationRepository = new EventLocationRepositoryImpl();

    public static EventLocationRepository getEventLocationRepository() {
        return eventLocationRepository;
    }

    
    
    
    
}
