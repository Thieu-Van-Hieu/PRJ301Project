/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import repository.DepartmentRepository;
import repository.impl.DepartmentRepositoryImpl;

/**
 *
 * @author ngoct
 */
public class DepartmentFactory {

    private static final DepartmentRepository departmentRepostory = new DepartmentRepositoryImpl();

    public static DepartmentRepository getDepermentRepository() {
        return departmentRepostory;
    }
}
