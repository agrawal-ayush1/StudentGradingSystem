package model;

public class ProjectCourse extends Course {
    public ProjectCourse(String courseCode, String courseName, int credits) {
        super(courseCode, courseName, credits);
    }
    private double getMarks(String assessmentName) {
        for (Assessment assessment : getAssessments()) {
            if (assessment.getAssessmentName().equalsIgnoreCase(assessmentName)){
                return assessment.getObtainedMarks();
            }
        }
        throw new IllegalStateException(
            "Assessment not found: " + assessmentName
        );
    }
    public double calculateFinalMarks() {
        double internalImplementation = getMarks("Internal Implementation");
        double internalReport = getMarks("Internal Report");
        double internalViva = getMarks("Internal Viva");
        double finalImplementation = getMarks("Final Implementation");
        double finalPresentation = getMarks("Final Presentation");
        double finalViva = getMarks("Final Viva");
        return internalImplementation + internalReport + internalViva + finalImplementation + finalPresentation + finalViva;
    }
}
