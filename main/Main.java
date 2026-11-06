package main;
import model.*;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("ST101", "Ayush");
        TheoryCourse theory = new TheoryCourse("CS101", "DS", 4);
        theory.addAssessment(new Assessment("CIE 1", "CIE", 30));
         theory.addAssessment(new Assessment("CIE 2", "CIE", 30));
        theory.addAssessment(new Assessment("Internal Assignment 1", "Assignment", 10));
        theory.addAssessment(new Assessment("Internal Assignment 2", "Assignment", 10));
        theory.addAssessment(new Assessment("SEE", "SEE", 100));
        theory.getAssessments().get(0).setObtainedMark(24);
        theory.getAssessments().get(1).setObtainedMark(26);
        theory.getAssessments().get(2).setObtainedMark(8);
        theory.getAssessments().get(3).setObtainedMark(9);
        theory.getAssessments().get(4).setObtainedMark(80);

        LabCourse lab = new LabCourse("IS102", "JAVA Lab", 2);
        lab.addAssessment(new Assessment("CIE Demo", "CIE", 30));
        lab.addAssessment(new Assessment("CIE Viva", "CIE", 20));
        lab.addAssessment(new Assessment("Assignment", "Assignment", 20));
        lab.addAssessment(new Assessment("SEE Demo", "SEE", 30));
        lab.addAssessment(new Assessment("SEE Viva", "SEE", 20));
        lab.getAssessments().get(0).setObtainedMark(25);
        lab.getAssessments().get(1).setObtainedMark(15);
        lab.getAssessments().get(2).setObtainedMark(18);
        lab.getAssessments().get(3).setObtainedMark(24);
        lab.getAssessments().get(4).setObtainedMark(16);

        ProjectCourse project = new ProjectCourse("IS123", "Mini Project", 3);
        project.addAssessment(new Assessment("Internal Implementation", "Internal", 20));
        project.addAssessment(new Assessment("Internal Report", "Internal", 10));
        project.addAssessment(new Assessment("Internal Viva", "Internal", 20));
        project.addAssessment(new Assessment("Final Implementation", "Final", 20));
        project.addAssessment(new Assessment("Final Presentation", "Final", 15));
        project.addAssessment(new Assessment("Final Viva", "Final", 15));
        project.getAssessments().get(0).setObtainedMark(18);
        project.getAssessments().get(1).setObtainedMark(8);
        project.getAssessments().get(2).setObtainedMark(16);
        project.getAssessments().get(3).setObtainedMark(19);
        project.getAssessments().get(4).setObtainedMark(13);
        project.getAssessments().get(5).setObtainedMark(14);

        student.addCourse(theory);
        student.addCourse(lab);
        student.addCourse(project);
        student.displayCourses();
        System.out.println("Course Results");
        for(Course course: student.getCourses()){
            System.out.println(course.getCourseName()+": "+course.calculateFinalMarks()+"/100");
        }
        System.out.println("SGPA: "+student.calcSGPA());
    }
}
