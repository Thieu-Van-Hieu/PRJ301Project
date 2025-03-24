/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hunggt1572004
 */
public class Club {

    private int id;
    private String name;
    private String description;
    private String type;
    private String date;
    private String avatarClub;
    private String coverImage;
    private String clubChairman;
    public Club() {
    }

    public Club(ClubBuilder clubBuilder) {
        this.id = clubBuilder.id;
        this.name = clubBuilder.name;
        this.description = clubBuilder.description;
        this.type = clubBuilder.type;
        this.date = clubBuilder.date;
        this.avatarClub = clubBuilder.avatarClub;
        this.coverImage = clubBuilder.coverImage;
        this.clubChairman = clubBuilder.clubChairman;       
    }
     
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getAvatarClub() {
        return avatarClub;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getClubChairman() {
        return clubChairman;
    }
    
    

    public static class ClubBuilder {

        private Club club;
        private int id;
        private String name;
        private String description;
        private String type;
        private String date;
        private String avatarClub;
        private String coverImage;
        private String clubChairman;
        public ClubBuilder(){        
        }

        public ClubBuilder setClub(Club club) {
            this.club = club;
            return this;
        }
        
        public ClubBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ClubBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ClubBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ClubBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public ClubBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public ClubBuilder setAvatarClub(String avatarClub) {
            this.avatarClub = avatarClub;
            return this;
        }

        public ClubBuilder setCoverImage(String coverImage) {
            this.coverImage = coverImage;
            return this;
        }

        public ClubBuilder setClubChairman(String clubChairman) {
            this.clubChairman = clubChairman;
            return this;
        }

        public Club build (){
            return new Club(this);
        }     
    }

}
