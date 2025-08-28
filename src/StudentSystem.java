import java.util.*;
import java.io.*;

public class StudentSystem {
    private Map<String, Student> students;

    public StudentSystem() {
        students = new HashMap<>();
    }

    public String addStudent(String studentId, String name, int age, String email) {
        if (studentId == null || studentId.isEmpty() || name == null || name.isEmpty() || email == null || !email.contains("@")) {
            return "Error: Invalid input data.";
        }
        if (students.containsKey(studentId)) {
            return "Error: Student ID already exists.";
        }
        try {
            students.put(studentId, new Student(studentId, name, age, email));
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
        return "Student added successfully.";
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    public String updateStudent(String studentId, String field, String newValue) {
        if (!students.containsKey(studentId)) {
            return "Error: Student not found.";
        }
        Student student = students.get(studentId);
        try {
            switch (field.toLowerCase()) {
                case "name":
                    student.setName(newValue);
                    break;
                case "age":
                    student.setAge(Integer.parseInt(newValue));
                    break;
                case "email":
                    student.setEmail(newValue);
                    break;
                default:
                    return "Error: Invalid field.";
            }
        } catch (NumberFormatException e) {
            return "Error: Invalid value for age.";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
        return "Student updated successfully.";
    }

    public String deleteStudent(String studentId) {
        if (students.remove(studentId) != null) {
            return "Student deleted successfully.";
        } else {
            return "Error: Student not found.";
        }
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students.values());
    }

    public String saveToFile(String filepath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(students);
            return "Data saved successfully.";
        } catch (IOException e) {
            return "Error saving data: " + e.getMessage();
        }
    }

    public String loadFromFile(String filepath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            Map<String, Student> loadedStudents = (Map<String, Student>) ois.readObject();
            students.clear(); // Clear existing data
            students.putAll(loadedStudents);
            return "Data loaded successfully.";
        } catch (IOException | ClassNotFoundException e) {
            return "Error loading data: " + e.getMessage();
        }
    }

    public List<Student> searchStudents(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Student> batchAddStudents(List<Student> studentList) {
        List<Student> addedStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (!students.containsKey(student.getStudentId())) {
                students.put(student.getStudentId(), student);
                addedStudents.add(student);
            }
        }
        return addedStudents;
    }

    public List<Student> sortStudents(String field, boolean ascending) {
        List<Student> sortedList = new ArrayList<>(students.values());
        sortedList.sort((s1, s2) -> {
            int comparison = 0;
            switch (field.toLowerCase()) {
                case "name":
                    comparison = s1.getName().compareToIgnoreCase(s2.getName());
                    break;
                case "age":
                    comparison = Integer.compare(s1.getAge(), s2.getAge());
                    break;
                case "email":
                    comparison = s1.getEmail().compareToIgnoreCase(s2.getEmail());
                    break;
            }
            return ascending ? comparison : -comparison;
        });
        return sortedList;
    }

    public Map<String, Double> getStatistics() {
        double totalAge = 0;
        for (Student student : students.values()) {
            totalAge += student.getAge();
        }
        double averageAge = students.isEmpty() ? 0 : totalAge / students.size();
        Map<String, Double> stats = new HashMap<>();
        stats.put("total_students", (double) students.size());
        stats.put("average_age", averageAge);
        return stats;
    }
}
