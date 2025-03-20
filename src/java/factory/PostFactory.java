/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.PostRepository;
import repository.impl.PostRepositoryImpl;

/**
 *
 * @author hunggt1572004
 */
public class PostFactory {
    private static final PostRepository postRepository = new PostRepositoryImpl();

    public static PostRepository getPostRepository() {
        return postRepository;
    }
    
}
