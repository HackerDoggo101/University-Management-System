
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.*;
import adt.ArrayList;
import adt.ArraySet;
import adt.HashMap;
import adt.ListInterface;
import adt.MapInterface;
import adt.SetInterface;
import boundary.StudentRegistrationManagementUI;
import dao.StudentDAO;
import java.util.Iterator;
import utility.MessageUI;
import dao.StudentInitializer;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jason
 */
public class StudentRegistrationManagement implements Serializable {

    private ListInterface<Student> studentList = new ArrayList<>();
    private CourseManagement courseManagement;
    private StudentDAO studentDAO = new StudentDAO("students.dat");
    private StudentRegistrationManagementUI studentUI = new StudentRegistrationManagementUI();

    public static int studentEntries;

    public StudentRegistrationManagement() {

        courseManagement = new CourseManagement();
        studentList = studentDAO.retrieveFromFile();

    }

    public void mainMenu() {
        int choice = 0;
        do {
            choice = studentUI.getMenuChoice();
            switch (choice) {
                case 0:
                    //to exit
                    MessageUI.displayBackMessage();
                    break;
                case 1:
                    studentEntries = studentList.getNumberOfEntries();

                    addStudent();
                    break;
                case 2:

                    removeStudent();
                    break;
                case 3:

                    amendStudent();
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    register();
                    break;
                case 7:
                    removeFromCourse();
                    break;
                case 8:
                    calFeesRegCourse();
                    break;
                case 9:
                    filterStudents();
                    break;
                case 10:
                    generateReport();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addStudent() {
        Student newStudent = studentUI.inputStudentDetails();
        studentList.add(newStudent);
        studentDAO.saveToFile(studentList);
    }

    public void removeStudent() {
        String studentId = studentUI.inputStudentID();
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Student student = studentList.getEntry(i);
            if (student.getStudentID().equals(studentId)) {
                studentList.remove(i);
                System.out.println("Student with ID " + studentId + " removed successfully.");
                studentDAO.saveToFile(studentList);
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public String getAllStudents() {
        String outputStr = "";
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            outputStr += studentList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    public void displayStudents() {
        if (!studentList.isEmpty()) {
            studentUI.listAllStudents(getAllStudents());
        } else {
            System.out.println("Student list is empty, please add new student to view.");
        }

    }

    public void amendStudent() {
        String studentId = studentUI.inputStudentID();
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Student student = studentList.getEntry(i);
            if (student.getStudentID().equals(studentId)) {
                int choice = 0;
                do {
                    choice = studentUI.getAmendChoice(studentId);
                    switch (choice) {
                        case 0:
                            MessageUI.displayBackMessage();
                            break;
                        case 1:
                            String studentName = studentUI.inputStudentName();
                            student.setStudentName(studentName);
                            MessageUI.displayUpdateMessage();
                            break;
                        case 2:
                            String studentDOB = studentUI.inputDOB();
                            student.setStudentDOB(studentDOB);
                            MessageUI.displayUpdateMessage();
                            break;
                        case 3:
                            String phoneNo = studentUI.inputPhoneNo();
                            student.setPhoneNo(phoneNo);
                            MessageUI.displayUpdateMessage();
                            break;
                        case 4:
                            String email = studentUI.inputEmail();
                            student.setStudentEmail(email);
                            MessageUI.displayUpdateMessage();
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();

                    }

                } while (choice != 0);
                studentDAO.saveToFile(studentList);

                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public void searchStudent() {

    }

    public void register() {
        String studentId = studentUI.inputStudentID();

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Student student = studentList.getEntry(i);
            if (student.getStudentID().equals(studentId)) {
                System.out.println("Valid student ID!");
                int choice = 0;
                do {
                    choice = studentUI.getRegChoice(studentId);
                    switch (choice) {
                        case 0:
                            MessageUI.displayBackMessage();
                            break;
                        case 1:
                            //display courses
                            courseManagement.displayAllCourses();
                            //type courseID and make payment
                            registerProcess(i);
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();

                    }

                } while (choice != 0);
                

                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public void registerProcess(int studentIndex) {

        String courseID;
        String type;
        Course course;
        SetInterface<String> courseStatuses = new ArraySet<>();
        boolean isValidType;
        Payment payment;
        String approve;

        //remember to use return at the last point
        do {
            courseID = studentUI.inputCourseID();
            if (courseManagement.getCourseMap().containsKey(courseID)) {

                //checks if the course has been registered by the student 
                //wrong
                if (isCourseAlreadyRegistered(studentList.getEntry(studentIndex), courseID)) {
                    System.out.println("This course is registered by the student!");
                } else {
                    System.out.println("Course Not registered by the student!");
                    course = courseManagement.getCourseMap().get(courseID);
                    courseStatuses = courseManagement.getCourseMap().get(courseID).getStatus();
                    // Get an iterator for the course statuses
                    Iterator<String> iterator;

                    do {
                        iterator = courseStatuses.getIterator();
                        isValidType = false;
                        type = studentUI.inputCourseType();

                        // Validate the type against the course statuses
                        while (iterator.hasNext()) {

                            String status = iterator.next();
                            if (type.equals(status)) {
                                isValidType = true;
                                break;
                            }
                        }

                        //if the course type entered is valid
                        if (isValidType) {
                            System.out.println("Course Type Valid!");
                            // The type matches one of the course statuses
                            // proceed to payment
                            payment = payment(courseManagement.getCourseMap().get(courseID).getCreditHours() * Registration.courseRate);
                            //test

                            do {

                                approve = studentUI.inputApprove();
                                if (approve.equals("Y")) {
                                    //print the registration bill
                                    System.out.println(payment);

                                    //generate the registration object then add into that student
                                    Registration registration = new Registration(course, type, payment);

                                    //add into student
                                    studentList.getEntry(studentIndex).getRegisteredCourses().put(registration.getRegNum(), registration);
                                    System.out.println(studentList.getEntry(studentIndex).getRegisteredCourses());

                                    studentDAO.saveToFile(studentList);
                                    //setRegisteredCourses(registeredCourses)   delete later
                                } else if (approve.equals("N")) {
                                    studentUI.printRejectedPayment();
                                } else {
//                                MessageUI.displayInvalidChoiceMessage();
                                    System.out.println("Invalid input!");
                                }

                            } while (!approve.equals("Y") && !approve.equals("N"));

                            return;

                        } else if (!type.equals("999")) {
                            System.out.println("Invalid course type for the selected course!");
                        }
                    } while (!type.equals("999"));

                }

            } else if (!courseID.equals("999")) {
                System.out.println("Invalid Course ID!");
            }

        } while (!courseID.equals("999"));

    }

    public void removeFromCourse() {

    }

    public void calFeesRegCourse() {

    }

    public void filterStudents() {

    }

    public void generateReport() {

    }

    public Payment payment(double amountToPay) {
        Scanner s1 = new Scanner(System.in);

        //Make Payment
        int paymentNum = -1; // Initialize to an invalid value

        do {
            try {

                paymentNum = studentUI.inputPaymentOption(amountToPay);

                if (paymentNum < 1 || paymentNum > 2) {
                    MessageUI.displayInvalidChoiceMessage();
                }
            } catch (InputMismatchException e) {
                // Handle the exception (non-integer input)
                MessageUI.displayInvalidChoiceMessage();
                s1.nextLine(); // Consume the invalid input
            }
        } while (paymentNum < 1 || paymentNum > 2);

        //paymentAmount = event object's price
        //Create Card object if paymentNum = 1, 2 for cash
        if (paymentNum == 1) {

            //cardNum
            String cardNum = studentUI.inputCardNumber();
            while (Card.vldCardNum(cardNum) == false) {
                System.out.print("Invalid Card Number!\n");
                cardNum = studentUI.inputCardNumber();
            }

            //cardHolder
            String cardHolder = studentUI.inputCardHolder();

            //cardExp
            String cardExp = studentUI.inputCardExp();
            while (Card.vldCardExp(cardExp) == false) {
                System.out.print("Invalid Card Expiry Date!\n");
                cardExp = studentUI.inputCardExp();
            }

            //cardCVV
            String cardCVV = studentUI.inputCardCVV();
            while (Card.vldCardCvv(cardCVV) == false) {
                System.out.print("Invalid Card CVV!\n");
                cardCVV = studentUI.inputCardCVV();
            }
            //Create Payment Object
            Card payment = new Card(cardNum, cardHolder, cardExp, cardCVV, amountToPay);

            return payment;

        } else {

            //amount tendered
            double amountTendered = -1; // Initialize to an invalid value

            do {
                try {

                    amountTendered = studentUI.inputAmountTendered();

                    if ((amountTendered < amountToPay && amountTendered > 0) || amountTendered < 0) {

                        MessageUI.displayInvalidInput();
                    }
                } catch (InputMismatchException e) {
                    // Handle the exception (non-numeric input)
                    MessageUI.displayInvalidInput();
                    MessageUI.displayOnlyNumeric();
                    s1.nextLine(); // Consume the invalid input
                }
            } while ((amountTendered < amountToPay && amountTendered > 0) || amountTendered < 0);

            //create cash object
            Cash payment = new Cash(amountTendered, amountToPay);
            //if amount tendered >= event.getPrice(), make the paid = true, if 0 = paid = false

//            else {
//
//                System.out.println(payment);
//            }
            return payment;

        }
    }

    // Method to check if the course is already registered by the student
    private static boolean isCourseAlreadyRegistered(Student student, String courseID) {
        // Get the registered courses of the student
        MapInterface<String, Registration> registeredCourses = student.getRegisteredCourses();

        // If registeredCourses is null, the course is not registered
        if (registeredCourses == null) {
            return false;
        }

        // Iterate through the registered courses
        for (Registration registration : registeredCourses.values()) {
            // Check if the registration contains the given course ID
            if (registration.getCourse().getCourseId().equals(courseID)) {
                // Course already registered
                return true;
            }
        }
        // Course not registered
        return false;
    }

}
