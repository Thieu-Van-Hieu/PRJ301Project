/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.EventLocationRepository;
import repository.EventRepository;
import repository.EventTypeRepository;
import repository.impl.EventLocationRepositoryImpl;
import repository.impl.EventRepositoryImpl;
import repository.impl.EventTypeRepositoryImpl;

/**
 *
 * @author hunggt1572004
 */
public class EventFactory {

    private static final EventRepository eventRepository = new EventRepositoryImpl();
    private static final EventLocationRepository eventLocationRepository = new EventLocationRepositoryImpl();
    private static final EventTypeRepository eventTypeRepository = new EventTypeRepositoryImpl();

    public static EventRepository getEventRepository() {
        return eventRepository;
    }

    public static EventLocationRepository getEventLocationRepository() {
        return eventLocationRepository;
    }

    public static EventTypeRepository getEventTypeRepository() {
        return eventTypeRepository;
    }
}
