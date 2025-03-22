/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author hunggt1572004
 */
public class MemberDTO {
    private int clubId;
    private int id;
    private int userId;
    private int deptId;
    private String role;
    public MemberDTO(int clubId, int id) {
        this.clubId = clubId;
        this.id = id;
    }

    public MemberDTO(int clubId, int userId, int deptId, String role) {
        this.clubId = clubId;
        this.userId = userId;
        this.deptId = deptId;
        this.role = role;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
    
    
}
