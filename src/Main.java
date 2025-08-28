import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentSystem studentSystem = new StudentSystem();
        CourseSystem courseSystem = new CourseSystem();
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem(studentSystem, courseSystem);

        Scanner scanner = new Scanner(System.in);
        String studentId; // Declare the variable outside the switch statement
        String courseId;  // Declare the variable outside the switch statement

        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine().trim();
                        System.out.println(studentSystem.addStudent(studentId, name, age, email));
                        break;
                    case "2":
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        Student student = studentSystem.getStudent(studentId);
                        if (student != null) {
                            System.out.println("Student ID: " + student.getStudentId());
                            System.out.println("Name: " + student.getName());
                            System.out.println("Age: " + student.getAge());
                            System.out.println("Email: " + student.getEmail());
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case "3":
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        System.out.print("Enter the field to update (name, age, email): ");
                        String field = scanner.nextLine().trim();
                        System.out.print("Enter new value for " + field + ": ");
                        String newValue = scanner.nextLine().trim();
                        System.out.println(studentSystem.updateStudent(studentId, field, newValue));
                        break;
                    case "4":
                        System.out.print("Enter Student ID to delete: ");
                        studentId = scanner.nextLine().trim();
                        System.out.println(studentSystem.deleteStudent(studentId));
                        break;
                    case "5":
                        List<Student> students = studentSystem.listStudents();
                        if (!students.isEmpty()) {
                            for (Student s : students) {
                                System.out.println("ID: " + s.getStudentId() + ", Name: " + s.getName() + ", Age: " + s.getAge() + ", Email: " + s.getEmail());
                            }
                        } else {
                            System.out.println("No students found.");
                        }
                        break;
                    case "6":
                        System.out.print("Enter the file path to save data: ");
                        String savePath = scanner.nextLine().trim();
                        System.out.println(studentSystem.saveToFile(savePath));
                        break;
                    case "7":
                        System.out.print("Enter the file path to load data from: ");
                        String loadPath = scanner.nextLine().trim();
                        System.out.println(studentSystem.loadFromFile(loadPath));
                        break;
                    case "8":
                        System.out.print("Enter the name to search for: ");
                        String searchName = scanner.nextLine().trim();
                        List<Student> foundStudents = studentSystem.searchStudents(searchName);
                        if (!foundStudents.isEmpty()) {
                            for (Student s : foundStudents) {
                                System.out.println("ID: " + s.getStudentId() + ", Name: " + s.getName() + ", Age: " + s.getAge() + ", Email: " + s.getEmail());
                            }
                        } else {
                            System.out.println("No students found with the name: " + searchName);
                        }
                        break;
                    case "9":
                        System.out.print("Enter student data in the format (ID, Name, Age, Email), separated by commas: ");
                        String batchData = scanner.nextLine().trim();
                        String[] studentsData = batchData.split(";");
                        List<Student> studentList = new ArrayList<>();
                        for (String data : studentsData) {
                            String[] parts = data.split(",");
                            studentList.add(new Student(parts[0].trim(), parts[1].trim(), Integer.parseInt(parts[2].trim()), parts[3].trim()));
                        }
                        List<Student> addedStudents = studentSystem.batchAddStudents(studentList);
                        System.out.println("Added " + addedStudents.size() + " students successfully.");
                        break;
                    case "10":
                        System.out.print("Enter the field to sort by (name, age, email): ");
                        String sortField = scanner.nextLine().trim();
                        System.out.print("Sort in ascending order? (y/n): ");
                        boolean ascending = scanner.nextLine().trim().equalsIgnoreCase("y");
                        List<Student> sortedStudents = studentSystem.sortStudents(sortField, ascending);
                        for (Student s : sortedStudents) {
                            System.out.println("ID: " + s.getStudentId() + ", Name: " + s.getName() + ", Age: " + s.getAge() + ", Email: " + s.getEmail());
                        }
                        break;
                    case "11":
                        Map<String, Double> stats = studentSystem.getStatistics();
                        System.out.println("Total Students: " + stats.get("total_students"));
                        System.out.println("Average Age: " + stats.get("average_age"));
                        break;
                    case "12":
                        System.out.print("Enter Course ID: ");
                        courseId = scanner.nextLine().trim();
                        System.out.print("Enter Course Name: ");
                        String courseName = scanner.nextLine().trim();
                        System.out.println(courseSystem.addCourse(courseId, courseName));
                        break;
                    case "13":
                        List<String> courses = courseSystem.listCourses();
                        if (!courses.isEmpty()) {
                            for (String course : courses) {
                                System.out.println(course);
                            }
                        } else {
                            System.out.println("No courses found.");
                        }
                        break;
                    case "14":
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        System.out.print("Enter Course ID: ");
                        courseId = scanner.nextLine().trim();
                        System.out.println(enrollmentSystem.enrollStudentInCourse(studentId, courseId));
                        break;
                    case "15":
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        System.out.print("Enter Course ID: ");
                        courseId = scanner.nextLine().trim();
                        System.out.println(enrollmentSystem.dropStudentFromCourse(studentId, courseId));
                        break;
                    case "16":
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        List<String> studentCourses = enrollmentSystem.listStudentCourses(studentId);
                        if (!studentCourses.isEmpty()) {
                            for (String course : studentCourses) {
                                System.out.println(course);
                            }
                        } else {
                            System.out.println("No courses found for student ID: " + studentId);
                        }
                        break;
                    case "17":
                        System.out.println("Exiting the system.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\n=== Student and Course Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. Retrieve Student");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. List All Students");
        System.out.println("6. Save to File");
        System.out.println("7. Load from File");
        System.out.println("8. Search Students by Name");
        System.out.println("9. Batch Add Students");
        System.out.println("10. Sort Students");
        System.out.println("11. Get Statistics");
        System.out.println("12. Add Course");
        System.out.println("13. List All Courses");
        System.out.println("14. Enroll Student in a Course");
        System.out.println("15. Drop Student from a Course");
        System.out.println("16. List a Student's Courses");
        System.out.println("17. Exit");
        System.out.println("=".repeat(60));
    }
}
