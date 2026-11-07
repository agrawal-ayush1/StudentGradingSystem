package main;

import java.util.Scanner;
import model.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Student student;

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("   STUDENT GRADING SYSTEM");
        System.out.println("=======================================");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine().trim();

        try {
            student = new Student(studentId, studentName);
        } catch (IllegalArgumentException e) {
            System.out.println("Error initializing student: " + e.getMessage());
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=======================================");
            System.out.println("             MAIN MENU");
            System.out.println("=======================================");
            System.out.println("1. Add Theory Course");
            System.out.println("2. Add Lab Course");
            System.out.println("3. Add Project Course");
            System.out.println("4. View Enrolled Courses");
            System.out.println("5. View Course Results & Final Marks");
            System.out.println("6. Calculate SGPA");
            System.out.println("7. Load Sample Data (Demo)");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");

            String choiceInput = scanner.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addTheoryCourse();
                    break;
                case 2:
                    addLabCourse();
                    break;
                case 3:
                    addProjectCourse();
                    break;
                case 4:
                    student.displayCourses();
                    break;
                case 5:
                    displayCourseResults();
                    break;
                case 6:
                    calculateAndDisplaySGPA();
                    break;
                case 7:
                    loadSampleData();
                    break;
                case 8:
                    System.out.println("Exiting Student Grading System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select between 1 and 8.");
                    break;
            }
        }
    }

    private static void addTheoryCourse() {
        System.out.println("\n--- Add Theory Course ---");
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine().trim();
        int credits = promptInt("Enter Credits: ", 1, 10);

        TheoryCourse course = new TheoryCourse(code, name, credits);

        course.addAssessment(createAssessment("CIE 1", "CIE", 30));
        course.addAssessment(createAssessment("CIE 2", "CIE", 30));
        course.addAssessment(createAssessment("Internal Assignment 1", "Assignment", 10));
        course.addAssessment(createAssessment("Internal Assignment 2", "Assignment", 10));
        course.addAssessment(createAssessment("SEE", "SEE", 100));

        student.addCourse(course);
        System.out.println("Theory course '" + name + "' added successfully!");
    }

    private static void addLabCourse() {
        System.out.println("\n--- Add Lab Course ---");
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine().trim();
        int credits = promptInt("Enter Credits: ", 1, 10);

        LabCourse course = new LabCourse(code, name, credits);

        course.addAssessment(createAssessment("CIE Demo", "CIE", 30));
        course.addAssessment(createAssessment("CIE Viva", "CIE", 20));
        course.addAssessment(createAssessment("Assignment", "Assignment", 20));
        course.addAssessment(createAssessment("SEE Demo", "SEE", 30));
        course.addAssessment(createAssessment("SEE Viva", "SEE", 20));

        student.addCourse(course);
        System.out.println("Lab course '" + name + "' added successfully!");
    }

    private static void addProjectCourse() {
        System.out.println("\n--- Add Project Course ---");
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine().trim();
        int credits = promptInt("Enter Credits: ", 1, 10);

        ProjectCourse course = new ProjectCourse(code, name, credits);

        course.addAssessment(createAssessment("Internal Implementation", "Internal", 20));
        course.addAssessment(createAssessment("Internal Report", "Internal", 10));
        course.addAssessment(createAssessment("Internal Viva", "Internal", 20));
        course.addAssessment(createAssessment("Final Implementation", "Final", 20));
        course.addAssessment(createAssessment("Final Presentation", "Final", 15));
        course.addAssessment(createAssessment("Final Viva", "Final", 15));

        student.addCourse(course);
        System.out.println("Project course '" + name + "' added successfully!");
    }

    private static Assessment createAssessment(String name, String category, double maxMarks) {
        Assessment assessment = new Assessment(name, category, maxMarks);
        System.out.print("Is student absent for " + name + " (Max: " + maxMarks + ")? (y/n): ");
        String ans = scanner.nextLine().trim().toLowerCase();
        if (ans.equals("y") || ans.equals("yes")) {
            assessment.markAbsent();
            System.out.println(name + " marked as Absent (0 marks).");
        } else {
            double marks = promptDouble("Enter marks for " + name + " (0 - " + maxMarks + "): ", 0, maxMarks);
            assessment.setObtainedMark(marks);
        }
        return assessment;
    }

    private static void displayCourseResults() {
        if (student.getCourses().isEmpty()) {
            System.out.println("No courses registered yet.");
            return;
        }
        System.out.println("\n--- Course Results ---");
        for (Course course : student.getCourses()) {
            try {
                double marks = course.calculateFinalMarks();
                System.out.printf("%s (%s): %.2f/100\n", course.getCourseName(), course.getCourseCode(), marks);
            } catch (Exception e) {
                System.out.printf("%s (%s): Error calculating marks (%s)\n", course.getCourseName(), course.getCourseCode(), e.getMessage());
            }
        }
    }

    private static void calculateAndDisplaySGPA() {
        try {
            double sgpa = student.calcSGPA();
            System.out.printf("\nSGPA: %.2f\n", sgpa);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot calculate SGPA: " + e.getMessage());
        }
    }

    private static void loadSampleData() {
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
        System.out.println("Sample courses (DS, JAVA Lab, Mini Project) loaded successfully!");
    }

    private static int promptInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.println("Value must be between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }

    private static double promptDouble(String message, double min, double max) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                double val = Double.parseDouble(input);
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.println("Value must be between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid decimal number. Please try again.");
            }
        }
    }
}
