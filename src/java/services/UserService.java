/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import factory.RepositoryFactory;
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
        this.userRepository = RepositoryFactory.getUserRepository();
        this.userInformationRepository = RepositoryFactory.getUserInformationRepository();
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
