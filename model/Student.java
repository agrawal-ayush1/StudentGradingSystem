package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import grading.GradingMethod;
public class Student {
    private String studentId;
    private String studentName;
    private List<Course> courses;
    public Student(String studentId,String studentName){
        if(studentId==null||studentId.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid Student Id");
        }
        if(studentName==null||studentName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid Student name");
        }
        this.studentId = studentId;
        this.studentName = studentName;
        this.courses = new ArrayList<>();
    }
    public String getStudentId(){
        return studentId;
    }
    public String getStudentName(){
        return studentName;
    }
    public List<Course> getCourses(){
        return Collections.unmodifiableList(courses);
    }
    public void addCourse(Course course){
        if(course==null){
            throw new IllegalArgumentException("Course cannot be null");
        }
        courses.add(course);
    }
    public void displayCourses(){
        System.out.println("Student Id: "+studentId);
        System.out.println("Student Name: "+studentName);
        System.out.println("Enrolled Courses");
        for(Course course:courses){
            System.out.println(course.getCourseCode()+"-"+course.getCourseName()+" ("+course.getCredits()+" credits");
        }
    }
    public double calcSGPA(){
        if(courses.isEmpty()){
            throw new IllegalArgumentException("No courses available");
        }
        double totalCreditPoints = 0;
        int totalCredits = 0;
        for(Course course:courses){
            double marks = course.calculateFinalMarks();
            int gradePoint = GradingMethod.calculateGP(marks);
            totalCreditPoints += course.getCredits() * gradePoint;
            totalCredits += course.getCredits();
        }
        return totalCreditPoints/totalCredits;
    }
}
