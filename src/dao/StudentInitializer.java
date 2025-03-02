/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Registration;
import entity.Student;
import java.io.Serializable;

/**
 *
 * @author Name: Yam Jason RDS2S2G3 22WMR13662
 */
public class StudentInitializer {

    private final StudentDAO studentDAO = new StudentDAO("students.dat");
    private ListInterface<Student> studentList = new ArrayList<>();

    public void initializeStudents() {

        studentList.add(new Student("Yam Jason", "17/07/2003", "030717101085", "016-8962213", "jason@gmail.com", "RDS"));
        studentList.add(new Student("Wong Yee En", "22/08/2003", "030822101394", "016-8972213", "yee@gmail.com", "RDS"));
        studentList.add(new Student("Tee Yong Zheng", "22/12/2003", "031222102021", "016-8982213", "tee@gmail.com", "RSW"));
        studentList.add(new Student("Yue Zhi Jving", "03/03/2003", "030303101085", "016-8992213", "jving@gmail.com", "RIS"));
        studentList.add(new Student("Darren Tan Chia Yuan", "04/01/2003", "030117101085", "016-9962213", "darren@gmail.com", "DIT"));
        studentList.add(new Student("Lai Weng Lok", "12/05/2003", "030720101085", "016-8963213", "wank@gmail.com", "DIS"));

        studentDAO.saveToFile(studentList);
        System.out.println("Student data initialized and saved to file.");
    }

}
