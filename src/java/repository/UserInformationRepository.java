/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import dto.UserInformationResponse;

/**
 *
 * @author ngoct
 */
public interface UserInformationRepository {

    public boolean isExistEmail(String email);
    
    public void addInformationOfUser(int userid, UserInformationResponse user);
    
    public boolean isExistStudentId(String studentId);
    
    public String getUsernameByEmail(String email);
    
    public UserInformationResponse getNameAvatarOfUser(int userId);
    
    public UserInformationResponse getUser(int userId);
    
    public UserInformationResponse getUserSetting(int userId);
    
    public void updateUserAvatar(UserInformationResponse user);
}
