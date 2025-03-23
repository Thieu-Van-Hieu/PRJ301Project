/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import entity.Department;

/**
 *
 * @author ngoct
 */
public interface DepartmentRepository {

    public Department getDepartmentByMemberId(int MemberID);
    
}
