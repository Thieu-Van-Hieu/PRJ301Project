/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ngoct
 */
public class UserInformationResponse {
    private int userId;
    private String userName;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String studentId;
    private String address;
    private String gender;
    private String birthday;
    private String avatar;
    
    
    public UserInformationResponse() {
    }

    public UserInformationResponse(String userName, String avatar) {
        this.userName = userName;
        this.avatar = avatar;
    }

    public UserInformationResponse(int userId, String avatar) {
        this.userId = userId;
        this.avatar = avatar;
    }  

    public UserInformationResponse(int userId, String userName, String password, String firstName, String lastName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
  
      
    public UserInformationResponse(String firstName, String lastName, String studentId, String address, String gender, String birthday, String avatarImg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.avatar = avatarImg;
    }

    public UserInformationResponse(int userId, String role, String firstName, String lastName, String email, String studentId, String address, String birthday, String avatar) {
        this.userId = userId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentId = studentId;
        this.address = address;
        this.birthday = birthday;
        this.avatar = avatar;
    }


    public UserInformationResponse(String userName, String password, String role, String firstName, String lastName, String email, String studentId, String address, String gender, String birthday) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentId = studentId;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
    }

   
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }   
    
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}
