package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private List<Assessment> assessments;

    public Course(String courseCode,String courseName,int credits){
        setCourseCode(courseCode);
        setCourseName(courseName);
        setCredits(credits);
        this.assessments = new ArrayList<>();
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getCourseName(){
        return courseName;
    }
    public int getCredits(){
        return credits;
    }
    public List<Assessment> getAssessments(){
        return Collections.unmodifiableList(assessments);
    }
    public void setCourseCode(String courseCode){
        if(courseCode==null||courseCode.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid course code");
        }
        this.courseCode = courseCode;
    }
    public void setCourseName(String courseName){
        if(courseName==null||courseName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid course name");
        }
        this.courseName=courseName;
    }
    public void setCredits(int credits){
        if(credits<=0){
            throw new IllegalArgumentException("Credits must be positive");
        }
        this.credits = credits;
    }
    public void addAssessment(Assessment assessment){
        if(assessment==null){
            throw new IllegalArgumentException("Assessment cannot be null");
        }
        assessments.add(assessment);
    }
    public abstract double calculateFinalMarks();
}
