package Services;

import Model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public static final List<Student> studentsDB = new ArrayList<>();


    static{

        /* Let's add some dummy data */
        Student s1 = new Student("945678124v", "Sadun ", "Colombo", "077-1234567", "abc@ijse.lk");
        Student s2 = new Student("961710065v", "Dulanga ", "Galle", "077-1234567", "abc@ijse.lk");
        Student s3 = new Student("965678135v", "Nuwan ", "Matara", "077-1234567", "abc@ijse.lk");
        Student s4 = new Student("975675674v", "Amara ", "Kandy", "077-1234567", "abc@ijse.lk");
        Student s5 = new Student("941358124v", "Kamal ", "Jaffna", "077-1234567", "abc@ijse.lk");
        studentsDB.add(s1);
        studentsDB.add(s2);
        studentsDB.add(s3);
        studentsDB.add(s4);
        studentsDB.add(s5);
    }

    public StudentService() {}

    public void saveStudent(Student student) {
        studentsDB.add(student);
    }

    public void updateStudent(Student student) {
        Student s = findStudent(student.getNic());
        int index = studentsDB.indexOf(s);
        studentsDB.set(index, student);
    }


    public List<Student> findAllStudents() {
        return studentsDB;
    }

    public Student findStudent(String registerID) {
        for (Student student : studentsDB) {

            if (student.getNic().equals(registerID)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findStudents(String query) {
        List<Student> result = new ArrayList<>();

        for (Student student : studentsDB) {

            if (student.getNic().contains(query) ||
                    student.getFullName().contains(query) ||
                    student.getAddress().contains(query) ||
                    student.getEmail().contains(query) ||
                    student.getContact().contains(query)) {
                result.add(student);
            }
        }
        return result;
    }


}
