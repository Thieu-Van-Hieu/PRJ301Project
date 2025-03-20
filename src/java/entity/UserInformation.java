/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ngoct
 */
public class UserInformation {

    private int id;
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String StudentId;
    private String address;
    private String gender;
    private String birthday;

    public UserInformation(Builder user) {
        this.id = user.id;
        this.userId = user.userId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.StudentId = user.StudentId;
        this.address = user.address;
        this.gender = user.gender;
        this.birthday = user.birthday;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLaseName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentId() {
        return StudentId;
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

    public static class Builder {

        private int id;
        private int userId;
        private String firstName;
        private String lastName;
        private String email;
        private String StudentId;
        private String address;
        private String gender;
        private String birthday;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLaseName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStudentId(String StudentId) {
            this.StudentId = StudentId;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserInformation build() {
            return new UserInformation(this);
        }
    }
}
