package model;

public class Assessment {
    private String assessmentName;
    private String category;
    private double maxMarks;
    private double obtainedMarks;
    private boolean absent;
    public Assessment(String assessmentName,String category,double maxMarks){
        if(assessmentName==null||assessmentName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid assessment name");
        }
        if(category==null||category.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid category");
        }
        if(maxMarks<=0){
            throw new IllegalArgumentException("Maximum marks must be positive");
        }
        this.assessmentName = assessmentName;
        this.category = category;
        this.maxMarks = maxMarks;
        this.obtainedMarks = 0;
        this.absent = false;
    }
    public String getAssessmentName(){
        return assessmentName;
    }
    public String getCategory(){
        return category;
    }
    public double getMaxMarks(){
        return maxMarks;
    }
    public double getObtainedMarks(){
        return obtainedMarks;
    }
    public boolean isAbsent(){
        return absent;
    }
    public void setObtainedMarks(double obtainedMarks){
        validate(obtainedMarks);
        this.obtainedMarks = obtainedMarks;
        this.absent = false;
    }
    private void validate(double marks){
        if(marks<0||marks>maxMarks){
            throw new IllegalArgumentException("Marks must be between 0 and "+maxMarks);
        }
    }
    public void markAbsent(){
        this.absent = true;
        this.obtainedMarks = 0;
    }
}
