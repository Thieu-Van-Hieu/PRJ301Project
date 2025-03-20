/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author hunggt1572004
 */
public class Post {

    private int id;
    private int clubId;
    private int userId;
    private String content;
    private Timestamp createdAt;
    private String img;
    private int loves;
    private int comments;
    ArrayList<PostComment> postCommentList;

    public Post() {
    }
    
    public Post(PostBuilder builder){
        this.id = builder.id;
        this.clubId = builder.clubId;
        this.userId = builder.userId;
        this.content = builder.content;
        this.img = builder.img;
        this.loves = builder.loves;
        this.comments = builder.comments;
        this.postCommentList = builder.postCommentList;
        this.createdAt = builder.createdAt;
        
    }

    public int getId() {
        return id;
    }

    public int getClubId() {
        return clubId;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getImg() {
        return img;
    }

    public int getLoves() {
        return loves;
    }

    public int getComments() {
        return comments;
    }

    public ArrayList<PostComment> getPostCommentList() {
        return postCommentList;
    }

    public static class PostBuilder {

        private int id;
        private int clubId;
        private int userId;
        private String content;
        private Timestamp createdAt;
        private String img;
        private int loves;
        private int comments;
        ArrayList<PostComment> postCommentList;

        public PostBuilder() {
        }

        public PostBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PostBuilder setClubId(int clubId) {
            this.clubId = clubId;
            return this;
        }

        public PostBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public PostBuilder setContent(String content) {
            this.content = content;
            return this; 
        }

        public PostBuilder setCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PostBuilder setImg(String img) {
            this.img = img;
            return this;
        }

        public PostBuilder setLoves(int loves) {
            this.loves = loves;
            return this;
        }

        public PostBuilder setComments(int comments) {
            this.comments = comments;
            return this;
        }

        public PostBuilder setPostCommentList(ArrayList<PostComment> postCommentList) {
            this.postCommentList = postCommentList;
            return this; 
        }       
        
        public Post build(){
            return new Post(this);
        }
    }
}
