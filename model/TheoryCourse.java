package model;

public class TheoryCourse extends Course{
    public TheoryCourse(String courseCode,String courseName,int credits){
        super(courseCode, courseName, credits);
    }
    private double getMarks(String assessmentName){
        for(Assessment assessment:getAssessments()){
            if(assessment.getAssessmentName().equalsIgnoreCase(assessmentName)){
                return assessment.getObtainedMarks();
            }
        }
        throw new IllegalArgumentException("Assessment not found: "+assessmentName);
    }
    public double calculateFinalMarks(){
        double cie1 = getMarks("CIE 1");
        double cie2 = getMarks("CIE 2");
        double assignment1 = getMarks("Internal Assignment 1");
        double assignment2 = getMarks("Internal Assignment 2");
        double see = getMarks("SEE");
        double cieAvg = (cie1+cie2)/2.0;
        double finalMarks = cieAvg+assignment1+assignment2+(see/2.0);
        return finalMarks;
    }
}
