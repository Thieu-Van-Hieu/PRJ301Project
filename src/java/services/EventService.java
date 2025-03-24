/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import factory.EventFactory;
import java.sql.Timestamp;
import repository.EventRepository;
import dto.EventResponse;
import dto.SearchEventDTO;
import java.util.ArrayList;
import entity.Event;
import entity.EventType;
import repository.EventLocationRepository;
import repository.EventTypeRepository;

/**
 *
 * @author hunggt1572004
 */
public class EventService {

    private final EventRepository eventRepository;
    private final EventLocationRepository eventLocationRepository;
    private final EventTypeRepository eventTypeRepository;

    public EventService() {
        this.eventRepository = EventFactory.getEventRepository();
        this.eventLocationRepository = EventFactory.getEventLocationRepository();
        this.eventTypeRepository = EventFactory.getEventTypeRepository();
    }

    public void addEvent(int clubId, int userId, String name, String description, int typeId, Timestamp startDate,
            Timestamp endDate, int locationId, String img) {
        eventRepository.addEvent(clubId, userId, name, description, typeId, startDate, endDate, locationId, img);
    }

    public ArrayList<EventResponse> getAllEventDescription() {
        return eventRepository.getEventDescription();
    }

    public ArrayList<EventResponse> getSearchEvent(SearchEventDTO searchEventDTO) {

        ArrayList<Event> searchServiceEntity = eventRepository.searchEvent(searchEventDTO);

        ArrayList<EventResponse> results = new ArrayList<>();
        for (Event x : searchServiceEntity) {
            EventResponse temp = new EventResponse();
            temp.setEventName(x.getName());
            temp.setClubName(x.getClubName());
            temp.setClubId(x.getClubId());
            temp.setStartDate(x.getStartDate());
            temp.setEndDate(x.getEndDate());
            temp.setEventId(x.getId());
            results.add(temp);
        }
        return results;
    }

    public ArrayList<EventType> selectAllEventType() {
        return eventTypeRepository.getAllEventType();
    }

    public void addEventLocation(String province, String district, String ward, String address) {
        eventLocationRepository.addEventLocation(province, district, ward, address);
    }

    public int getLocationId(String province, String district, String ward, String address) {
        return eventLocationRepository.getEventLocationId(province, district, ward, address);
    }

    public boolean deleteEvent(int eventId) {
        return eventRepository.deleteEvent(eventId);

    }
}
