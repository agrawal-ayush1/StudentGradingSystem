package model;

public class LabCourse extends Course{
    public LabCourse(String courseCode,String courseName,int credits){
        super(courseCode,courseName,credits);
    }
    private double getMarks(String assessmentName){
        for(Assessment assessment:getAssessments()){
            if(assessment.getAssessmentName().equalsIgnoreCase(assessmentName)){
                return assessment.getObtainedMarks();
            }
        }
        throw new IllegalStateException("Assessment not Found: "+assessmentName);
    }
    public double calculateFinalMarks(){
        double cieDemo = getMarks("CIE Demo");
        double cieViva = getMarks("CIE Viva");
        double assignment = getMarks("Assignment");
        double seeDemo = getMarks("SEE Demo");
        double seeViva = getMarks("SEE Viva");
        double internalMarks = ((cieDemo + cieViva) / 50.0) * 30.0;
        double seeMarks = seeDemo + seeViva;
        return internalMarks + assignment + seeMarks;
    }
}
