package Services;

import Model.Student;
import Services.exception.DuplicateEntryException;
import Services.exception.FailedOperationException;
import Services.exception.NotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static final File dbFile = new File("Student.db");
    private static List<Student> studentsList = new ArrayList<>();


    //    static{
//
//        /* Let's add some dummy data */
//        Student s1 = new Student("945678124v", "Sadun ", "Colombo", "077-1234567", "abc@ijse.lk");
//        Student s2 = new Student("961710065v", "Dulanga ", "Galle", "077-1234567", "abc@ijse.lk");
//        Student s3 = new Student("965678135v", "Nuwan ", "Matara", "077-1234567", "abc@ijse.lk");
//        Student s4 = new Student("975675674v", "Amara ", "Kandy", "077-1234567", "abc@ijse.lk");
//        Student s5 = new Student("941358124v", "Kamal ", "Jaffna", "077-1234567", "abc@ijse.lk");
//        studentsDB.add(s1);
//        studentsDB.add(s2);
//        studentsDB.add(s3);
//        studentsDB.add(s4);
//        studentsDB.add(s5);
//    }

    static {
        readDataFromFile();
    }

    public StudentService() {}

    public void saveStudent(Student student) throws DuplicateEntryException, FailedOperationException {
        if(exitsStudent(student.getNic())){
            throw new DuplicateEntryException();
        }
        try {
            studentsList.add(student);
            writeDataToFile();
        }catch (FailedOperationException e){
            studentsList.remove(student);
            throw e;
        }
    }

    public void updateStudent(Student student) throws NotFoundException, FailedOperationException {
        Student s = findStudent(student.getNic());
        int index = studentsList.indexOf(s);
        try {
            studentsList.set(index, student);
            writeDataToFile();
        }catch (FailedOperationException e){
            studentsList.set(index, s);
            throw e;
        }
    }


    public List<Student> findAllStudents() {
        return studentsList;
    }

    private boolean exitsStudent(String nic){
        for(Student student: studentsList){
            if(student.getNic().equals(nic)){
                return true;
            }
        }
        return false;
    }
    public Student findStudent(String registerID) {
        for (Student student : studentsList) {

            if (student.getNic().equals(registerID)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findStudents(String query) {
        List<Student> result = new ArrayList<>();

        for (Student student : studentsList) {

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


    private void writeDataToFile() throws FailedOperationException {
        try(FileOutputStream fos = new FileOutputStream(dbFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(studentsList);
        }catch (Throwable e){
            e.printStackTrace();
            throw new FailedOperationException();
        }
    }

    private static void readDataFromFile(){

        if(!dbFile.exists()) return;


        try(FileInputStream fis = new FileInputStream(dbFile);
            ObjectInputStream ois = new ObjectInputStream(fis)){

            studentsList = (ArrayList<Student>) ois.readObject();

        }catch (IOException | ClassNotFoundException e) {
            if(e instanceof EOFException){
                dbFile.delete();
            }else{
                e.printStackTrace();
            }
        }
    }


}
