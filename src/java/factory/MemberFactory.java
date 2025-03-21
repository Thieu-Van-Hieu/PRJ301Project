/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.MemberRepository;
import repository.impl.MemberRepositoryImpl;

/**
 *
 * @author hunggt1572004
 */
public class MemberFactory {
    private static final MemberRepository memberRepository = new MemberRepositoryImpl();

    public static MemberRepository getMemberRepository() {
        return memberRepository;
    }
    
    
}
