package repository;

import dto.MemberTaskResponse;
import entity.Member;
import java.util.ArrayList;


public interface MemberTaskRepository {
    public ArrayList<MemberTaskResponse> getMembers(Member member); 
}
