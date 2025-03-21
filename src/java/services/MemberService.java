/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.MemberResponse;
import factory.MemberFactory;
import java.util.ArrayList;
import repository.MemberRepository;

/**
 *
 * @author hunggt1572004
 */
public class MemberService {
    private final MemberRepository memberRepository;
    
    public MemberService(){
        memberRepository = MemberFactory.getMemberRepository();
    }
    
    public ArrayList<MemberResponse> getAllMemberOfClub(int clubId){
        return memberRepository.getAllMemberOfClub(clubId);
    };
}
