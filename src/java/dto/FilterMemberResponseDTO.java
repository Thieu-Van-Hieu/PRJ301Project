/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ngoct
 */
public class FilterMemberResponseDTO {

    private String StudentID;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String gender;
    private String department;
    private String roleClub;

    public FilterMemberResponseDTO() {
    }

    public FilterMemberResponseDTO(String StudentID, String firstName, String lastName, String birthdate, String gender, String department, String roleClub) {
        this.StudentID = StudentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.department = department;
        this.roleClub = roleClub;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoleClub() {
        return roleClub;
    }

    public void setRoleClub(String roleClub) {
        this.roleClub = roleClub;
    }

}
