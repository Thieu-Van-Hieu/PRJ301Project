/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import entity.User;

/**
 *
 * @author ngoct
 */
public interface UserRepository {
      public boolean isLogin(String username, String password);
      public User getUsername(String username);
}
