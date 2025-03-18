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

    public UserInformationResponse(UserInfor user) {
        this.userName = user.userName;
        this.password = user.password;
        this.role = user.role;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.studentId = user.studentId;
        this.address = user.address;
        this.gender = user.gender;
        this.birthday = user.birthday;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public static class UserInfor {

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

        public UserInfor() {
        }

        public UserInfor setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserInfor setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserInfor setRole(String role) {
            this.role = role;
            return this;
        }

        public UserInfor setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserInfor setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserInfor setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserInfor setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public UserInfor setAddress(String address) {
            this.address = address;
            return this;
        }

        public UserInfor setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserInfor setBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserInformationResponse build() {
            return new UserInformationResponse(this);
        }
    }
}
