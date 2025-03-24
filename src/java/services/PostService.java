/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.CommentDTO;
import dto.PostDTO;
import entity.Post;
import factory.PostFactory;
import java.util.ArrayList;
import repository.PostRepository;

/**
 *
 * @author hunggt1572004
 */
public class PostService {
    private final PostRepository postRepository;

    public PostService() {
        this.postRepository = PostFactory.getPostRepository();
    }
    
    public void addPost(PostDTO postDTO){
        postRepository.addPost(postDTO);
    }
    
    public ArrayList<Post> getAllPostOfClub(int clubId){
        return postRepository.getAllPostOfClub(clubId);
    }
    
    public void addLove(int postId, int userId){
        postRepository.addLove(postId, userId);
    }
    
    public void addComment(CommentDTO commentDTO){
        postRepository.addComment(commentDTO);
    }
    
    public boolean isLove(int postId, int userId){
        return postRepository.isLove(postId, userId);
    }
}
