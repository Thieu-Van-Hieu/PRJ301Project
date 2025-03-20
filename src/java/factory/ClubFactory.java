/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.ClubRepository;
import repository.impl.ClubRepositoryImpl;

/**
 *
 * @author hunggt1572004
 */
public class ClubFactory {
    private static final ClubRepository clubRepository = new ClubRepositoryImpl();

    public static ClubRepository getClubRepository() {
        return clubRepository;
    }
    
}
