import java.util.*;

public class CourseSystem {
    private final Map<String, String> courses;

    public CourseSystem() {
        courses = new HashMap<>();
    }

    public String addCourse(String courseId, String courseName) {
        if (courseId == null || courseId.isEmpty() || courseName == null || courseName.isEmpty()) {
            return "Error: Invalid input data.";
        }
        if (courses.containsKey(courseId)) {
            return "Error: Course ID already exists.";
        }
        courses.put(courseId, courseName);
        return "Course added successfully.";
    }

    public boolean courseExists(String courseId) {
        return courses.containsKey(courseId);
    }

    public String getCourseName(String courseId) {
        return courses.get(courseId);
    }

    public List<String> listCourses() {
        List<String> courseList = new ArrayList<>();
        for (Map.Entry<String, String> entry : courses.entrySet()) {
            courseList.add("Course ID: " + entry.getKey() + ", Course Name: " + entry.getValue());
        }
        return courseList;
    }
}
