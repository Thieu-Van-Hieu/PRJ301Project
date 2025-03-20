/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.ClubResponse;
import entity.Club;
import factory.ClubFactory;
import java.util.ArrayList;
import repository.ClubRepository;

/**
 *
 * @author hunggt1572004
 */
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService() {
        this.clubRepository = ClubFactory.getClubRepository();
    }
    
    public ArrayList<ClubResponse> selectAllClubIdAndClubName(){
        ArrayList<ClubResponse> clubResponseInformations = new ArrayList<>();
        
        ArrayList<Club> clubInfors = clubRepository.getAllClubIdAndName();
        
        for (Club clubInfor : clubInfors) {
            ClubResponse clubResponse = new ClubResponse(clubInfor.getId(), clubInfor.getName());
            clubResponseInformations.add(clubResponse);
        }
        
        return clubResponseInformations;
    }
    
    public ArrayList<ClubResponse> selectAllClubInformations(){
        return clubRepository.getAllClubInformations();
    }
    
    public ArrayList<ClubResponse> selectAllClubItems(int userId){
        return clubRepository.getClubIcons(userId);
    }
}
