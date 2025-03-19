/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hunggt1572004
 */
public class EventLocation {
    private int eventLocationId;
    private String province;
    private String district;
    private String ward;
    private String address;

    public EventLocation() {
    }

    public EventLocation(int eventLocationId, String province, String district, String ward, String address) {
        this.eventLocationId = eventLocationId;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.address = address;
    }

    public int getEventLocationId() {
        return eventLocationId;
    }

    public void setEventLocationId(int eventLocationId) {
        this.eventLocationId = eventLocationId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
