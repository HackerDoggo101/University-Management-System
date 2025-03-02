/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import adt.HashMap;
import adt.ListInterface;
import adt.MapInterface;
import control.StudentRegistrationManagement;
import dao.StudentDAO;
import java.io.Serializable;

/**
 *
 * @author Name: Yam Jason RDS2S2G3 22WMR13662
 */
public class Student implements Serializable {

    private String studentID;
    private String studentName;
    private String studentDOB;
    private String ic;
    private String phoneNo;
    private String studentEmail;
    private String programmeID;
    private MapInterface<String, Registration> registeredCourses;
    private boolean withdraw;

    private static int nextStudentID;

    public Student() {

    }

    public Student(String studentName, String studentBOD, String ic, String phoneNo, String studentEmail, String programmeID) {
        nextStudentID = StudentRegistrationManagement.studentEntries++;

        this.studentName = studentName;
        this.phoneNo = phoneNo;
        this.studentDOB = studentBOD;
        this.studentEmail = studentEmail;
        this.programmeID = programmeID;
        this.ic = ic;
        registeredCourses = new HashMap<>();
        this.studentID = "S" + (nextStudentID + 100);
        this.withdraw = false;

    }

    public String getStudentID() {
        return studentID;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(String programmeID) {
        this.programmeID = programmeID;
    }

    public boolean isWithdraw() {
        return withdraw;
    }

    public void setWithdraw(boolean withdraw) {
        this.withdraw = withdraw;
    }
    

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getIc() {
        return ic;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentDOB() {
        return studentDOB;
    }

    public void setStudentDOB(String studentBOD) {
        this.studentDOB = studentBOD;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public MapInterface<String, Registration> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(MapInterface<String, Registration> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public void addRegisteredCourses(String regNum, Registration registration) {
        registeredCourses.put(regNum, registration);
    }

    public void removeRegisteredCourses(String regNum) {
        registeredCourses.remove(regNum);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-25s %-10s %-15s %-20s %-15s", studentID, studentName, studentDOB, phoneNo, studentEmail, programmeID);

    }

}
