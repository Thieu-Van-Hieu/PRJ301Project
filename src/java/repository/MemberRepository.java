/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import dto.MemberDTO;
import dto.MemberResponse;
import entity.Member;
import java.util.ArrayList;

/**
 *
 * @author hunggt1572004
 */
public interface MemberRepository {
    public ArrayList<MemberResponse> getAllMemberOfClub(int clubId);
    public void deleteMemberOfClub(MemberDTO memberDTO);
    
    public Member getMember(int userId, int clubId);
}
