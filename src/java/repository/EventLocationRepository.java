/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

/**
 *
 * @author hunggt1572004
 */
public interface EventLocationRepository {
    public void addEventLocation(String province, String district, String ward, String address);
    
    public int getEventLocationId(String province, String district, String ward, String address);
}
