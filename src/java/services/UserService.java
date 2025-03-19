/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.ForgotPasswordResponse;
import dto.UserInformationResponse;
import factory.UserFactory;
import repository.UserRepository;
import entity.User;
import repository.UserInformationRepository;

/**
 *
 * @author ngoct
 */
public class UserService {

    private final UserRepository userRepository;

    private final UserInformationRepository userInformationRepository;

    private static UserInformationResponse response = new UserInformationResponse();

    public UserService() {
        this.userRepository = UserFactory.getUserRepository();
        this.userInformationRepository = UserFactory.getUserInformationRepository();
    }

    public boolean checkLogin(String username, String password) {
        return userRepository.isLogin(username, password);
    }

    public User getUsername(String username, String password) {
        response.setUserName(username);
        response.setPassword(password);
        return userRepository.getUsername(username);
    }

    public boolean isExistEmail(String email) {
        response.setEmail(email);
        return userInformationRepository.isExistEmail(email);
    }

    public int getUserId() {
        return userRepository.getUserId(response.getUserName());
    }

    public void addUserRoleMember() {
        userRepository.addUserRoleMember(response.getUserName(), response.getPassword());
    }

    public void addInformationOfUser() {
        userInformationRepository.addInformationOfUser(getUserId(), response);
    }

    public void addByParamIntoResponse(UserInformationResponse infor) {
        response.setFirstName(infor.getFirstName());
        response.setLastName(infor.getLastName());
        response.setGender(infor.getGender());
        response.setStudentId(infor.getStudentId());
        response.setAddress(infor.getAddress());
        response.setBirthday(infor.getBirthday());
    }

    public boolean isExistStudentId(String studentId) {
        return userInformationRepository.isExistStudentId(studentId);
    }

    public UserInformationResponse getResults() {
        return response;
    }

    public String getUsernameByEmail() {
        response.setUserName(userInformationRepository.getUsernameByEmail(response.getEmail()));
        return userInformationRepository.getUsernameByEmail(response.getEmail());
    }

    public void resetPassword(String newPassword) {
        userRepository.resetPassword(response.getUserName(), newPassword);
    }
}
