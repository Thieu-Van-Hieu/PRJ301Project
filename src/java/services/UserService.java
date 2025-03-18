/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

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

    public UserService() {
        this.userRepository = UserFactory.getUserRepository();
        this.userInformationRepository = UserFactory.getUserInformationRepository();
    }

    public boolean checkLogin(String username, String password) {
        return userRepository.isLogin(username, password);
    }

    public User getUsername(String username) {
        return userRepository.getUsername(username);
    }

    public boolean isExistEmail(String email) {
        return userInformationRepository.isExistEmail(email);
    }
    
}
