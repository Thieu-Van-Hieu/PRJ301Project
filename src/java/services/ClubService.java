/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.ClubResponse;
import entity.Club;
import factory.ClubFactory;
import java.awt.geom.CubicCurve2D;
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
    
    public String clubName(int clubId){
        ArrayList<Club> clubInfors = clubRepository.getAllClubIdAndName();
        for (Club clubInfor : clubInfors) {
            if(clubInfor.getId() == clubId){
                return clubInfor.getName();
            }
        }
        return null;
    }
    
    public int addClub(ClubResponse club){
        return clubRepository.addClub(club);
    }
    
    public ArrayList<ClubResponse> getClubsByType(String type){
        return clubRepository.getClubsByType(type);
    }
    
    public String getCoverImg(int clubId){
        ArrayList<Club> clubInfors = clubRepository.getAllClubIdAndName();
        for (Club clubInfor : clubInfors) {
            if(clubInfor.getId() == clubId){
                return clubInfor.getCoverImage();
            }
        }
        return null;
    }
    
    public ArrayList<Club> getAllClubForAdmin() {
        return clubRepository.getAllClubAdmin();
    }
    
    public void deleteClubFromAdmin(int clubId) {
        clubRepository.deleteClub(clubId);
    }
}
