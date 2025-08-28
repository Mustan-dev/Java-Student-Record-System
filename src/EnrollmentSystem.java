import java.util.*;

public class EnrollmentSystem {
    private final Map<String, List<String>> enrollments;
    private final StudentSystem studentSystem;
    private final CourseSystem courseSystem;

    public EnrollmentSystem(StudentSystem studentSystem, CourseSystem courseSystem) {
        this.studentSystem = studentSystem;
        this.courseSystem = courseSystem;
        enrollments = new HashMap<>();
    }

    public String enrollStudentInCourse(String studentId, String courseId) {
        if (studentSystem.getStudent(studentId) == null) {
            return "Error: Student ID not found.";
        }
        if (!courseSystem.courseExists(courseId)) {
            return "Error: Course ID not found.";
        }
        enrollments.computeIfAbsent(studentId, k -> new ArrayList<>()).add(courseId);
        return "Student enrolled in course successfully.";
    }

    public String dropStudentFromCourse(String studentId, String courseId) {
        if (!enrollments.containsKey(studentId) || !enrollments.get(studentId).contains(courseId)) {
            return "Error: Enrollment not found.";
        }
        enrollments.get(studentId).remove(courseId);
        return "Student dropped from course successfully.";
    }

    public List<String> listStudentCourses(String studentId) {
        if (!enrollments.containsKey(studentId)) {
            return new ArrayList<>();
        }
        List<String> coursesList = new ArrayList<>();
        for (String courseId : enrollments.get(studentId)) {
            String courseName = courseSystem.getCourseName(courseId);
            if (courseName != null) {
                coursesList.add("Course ID: " + courseId + ", Course Name: " + courseName);
            }
        }
        return coursesList;
    }
}
