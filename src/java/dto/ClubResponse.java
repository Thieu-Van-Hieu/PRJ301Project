/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author hunggt1572004
 */
public class ClubResponse {

    private int id;
    private String name;
    private String description;
    private String type;
    private String date;
    private String avatarClub;
    private String coverImage;
    private String clubPresidentName;
    private int memberId;
    
    public ClubResponse() {
    }

    public ClubResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }   

    public ClubResponse(int id, String name, String description, String type, String avatarClub, String coverImage, String clubPresidentName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.avatarClub = avatarClub;
        this.coverImage = coverImage;
        this.clubPresidentName = clubPresidentName;
    }

    public ClubResponse(String name, String description, String type, String date, String avatarClub, String coverImage) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.date = date;
        this.avatarClub = avatarClub;
        this.coverImage = coverImage;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
       
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvatarClub() {
        return avatarClub;
    }

    public void setAvatarClub(String avatarClub) {
        this.avatarClub = avatarClub;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getClubPresidentName() {
        return clubPresidentName;
    }
    
    

}
