/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import dto.CommentDTO;
import dto.PostDTO;
import entity.Post;
import java.util.ArrayList;

/**
 *
 * @author hunggt1572004
 */
public interface PostRepository {
    public ArrayList<Post> getAllPostOfClub(int clubId);
    
    public void addPost(PostDTO postDTO);
    
    public void addLove(int postId, int memberId);
    
    public boolean isLove(int postId, int userId);
    
    public void addComment(CommentDTO commentDTO);
    
    public void deletePost(int postId);
}
