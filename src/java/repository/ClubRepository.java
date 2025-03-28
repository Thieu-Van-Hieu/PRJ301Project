/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import dto.ClubResponse;
import entity.Club;
import java.util.ArrayList;

/**
 *
 * @author hunggt1572004
 */
public interface ClubRepository {
    public ArrayList<Club> getAllClubIdAndName();
    
    public ArrayList<ClubResponse> getAllClubInformations();
    
    public ArrayList<ClubResponse> getClubIcons(int userId);
    
    public int addClub(ClubResponse club);
    
    public ArrayList<ClubResponse> getClubsByType(String type);
    
    public ArrayList<Club> getAllClubAdmin();
    
    public void deleteClub (int clubId);
}
