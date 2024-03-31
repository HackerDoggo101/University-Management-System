/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.Course;

/**
 *
 * @author wongy
 */
public class CourseInitializer {
    
    
    public MapInterface<String,Course> initializeCourses(){
        
        MapInterface<String,Course> courseMap = new HashMap<>();
        
        
        ListInterface<String> mainResit = new ArrayList<>();
        mainResit.add("Main");
        mainResit.add("Resit");

        
        ListInterface<String> mainResitRepeat = new ArrayList<>();
        mainResitRepeat.add("Main");
        mainResitRepeat.add("Resit");
        mainResitRepeat.add("Repeat");
        
        ListInterface<String> mainRepeat = new ArrayList<>();
        mainRepeat.add("Main");
        mainRepeat.add("Repeat");
        
        ListInterface<String> mainResitRepeatElective = new ArrayList<>();
        mainResitRepeatElective.add("Main");
        mainResitRepeatElective.add("Resit");
        mainResitRepeatElective.add("Repeat");
        mainResitRepeatElective.add("Elective");
        
        // Initialize Courses
        courseMap.put("BAIT1023", new Course("BAIT1023","Web Design and Development",mainResitRepeat,3));
        courseMap.get("BAIT1023").setStatus(mainResit);

        courseMap.put("BACS1053", new Course("BACS1053","Database Management",mainResitRepeat,4));
        courseMap.put("BACS2023", new Course("BACS2023","Object-Oriented Programming",mainResitRepeatElective,4));
        courseMap.put("BJEL1023", new Course("BJEL1023","Academic English",mainResit,3));
        courseMap.put("BJEL1013", new Course("BJEL1013","English For Tertiary Studies",mainRepeat,3));
        courseMap.put("BFAI233", new Course("BFAI1233","Introduction to Economy",mainResitRepeatElective,4));
//        
        
        return courseMap;
    }
    
//    public static void main(String[] args) {
//    // To illustrate how to use the initializeProducts() method
//        CourseInitializer c = new CourseInitializer();
//    MapInterface<String,Course> courseMap = c.initializeCourses();
//    System.out.println("\nCourses:\n" + courseMap.toString());
    
    
 // }
}
