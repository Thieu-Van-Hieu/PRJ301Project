/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.FilterMemberDTO;
import dto.FilterMemberResponseDTO;
import dto.MemberDTO;
import dto.MemberResponse;
import entity.Member;
import entity.UserInformation;
import factory.MemberFactory;
import java.util.ArrayList;
import repository.DepartmentRepository;
import repository.MemberRepository;
import factory.DepartmentFactory;
import factory.UserFactory;
import repository.UserInformationRepository;
import entity.Department;

/**
 *
 * @author hunggt1572004
 */
public class MemberService {

    private final MemberRepository memberRepository;

    private final DepartmentRepository departmentRepository;

    private final UserInformationRepository userInformation;

    public MemberService() {
        memberRepository = MemberFactory.getMemberRepository();
        departmentRepository = DepartmentFactory.getDepermentRepository();
        userInformation = UserFactory.getUserInformationRepository();
    }

    public ArrayList<MemberResponse> getAllMemberOfClub(int clubId) {
        return memberRepository.getAllMemberOfClub(clubId);
    }

    ;
    
    public Member getMemberInfor(int userId, int clubId) {
        return memberRepository.getMember(userId, clubId);
    }

    public void addMember(MemberDTO member) {
        memberRepository.addMember(member);
    }

    public ArrayList<FilterMemberResponseDTO> filterMember(FilterMemberDTO filterMemberDTO) {
        ArrayList<FilterMemberResponseDTO> results = new ArrayList<>();

        ArrayList<Member> member = memberRepository.filterMember(filterMemberDTO);
        for (Member x : member) {
            FilterMemberResponseDTO temp = new FilterMemberResponseDTO();

            UserInformation userInformation = this.userInformation.getInformationByMemberId(x.getId());

            Department deparment = this.departmentRepository.getDepartmentByMemberId(x.getId());

            temp.setFirstName(userInformation.getFirstName());
            temp.setBirthdate(userInformation.getBirthday());
            temp.setDepartment(deparment.getName());
            temp.setGender(userInformation.getGender());
            temp.setStudentID(userInformation.getStudentId());
            temp.setRoleClub(x.getRole());
            temp.setLastName(userInformation.getLaseName());

            results.add(temp);
        }
        return results;

    }
}
