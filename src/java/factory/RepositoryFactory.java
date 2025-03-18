/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.UserInformationRepository;
import repository.UserRepository;
import repository.repositoryImpl.UserInformationRepositoryImpl;
import repository.repositoryImpl.UserRepositoryImpl;

/**
 *
 * @author ngoct
 */
public class RepositoryFactory {

    private static final UserRepository userRepository = new UserRepositoryImpl();
    private static final UserInformationRepository userInformationRepository = new UserInformationRepositoryImpl();

    public static UserRepository getUserRepository() {
        return userRepository;
    }
    public static UserInformationRepository getUserInformationRepository() {
        return userInformationRepository;
    }
}
