package grading;

public class GradingMethod {
    public static int calculateGP(double marks){
        if(marks<0||marks>100){
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
        if(marks>=90) return 10;
        else if(marks>=80) return 9;
        else if(marks>=70) return 8;
        else if(marks>=60) return 7;
        else if(marks>=50) return 6;
        else if(marks>=40) return 5;
        else return 0;
    }
}
