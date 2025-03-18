/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import repository.EventLocationRepository;
import factory.EventLocationFactory;
/**
 *
 * @author hunggt1572004
 */
public class EventLocationService {
    private final EventLocationRepository eventLocationRepository;

    public EventLocationService() {
        this.eventLocationRepository = EventLocationFactory.getEventLocationRepository();
    }
    
    public void addEventLocation(String province, String district, String ward, String address){
        eventLocationRepository.addEventLocation(province, district, ward, address);
    }
    
    public int getLocationId(String province, String district, String ward, String address){
        return eventLocationRepository.getEventLocationId(province, district, ward, address);
    }
   
    
    
}
