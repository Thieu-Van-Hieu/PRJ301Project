/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author ngoct
 */
public class FilterMemberDTO {

    private int departmentId;
    private String gender;
    private Integer ageFrom;
    private Integer ageTo;
    private String dateJoin;
    private String nameSearch;

    public FilterMemberDTO() {
    }

    public FilterMemberDTO(int departmentId, String gender, Integer ageFrom, Integer ageTo, String dateJoin, String nameSearch) {
        this.departmentId = departmentId;
        this.gender = gender;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.dateJoin = dateJoin;
        this.nameSearch = nameSearch;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(Integer ageFrom) {
        this.ageFrom = ageFrom;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    public String getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(String dateJoin) {
        this.dateJoin = dateJoin;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

}
