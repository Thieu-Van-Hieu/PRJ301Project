/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

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
}
