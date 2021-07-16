package Services;

import Model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    public static final List<Admin> adminDB = new ArrayList<>();


    static {

        /* Let's add some dummy data */
        Admin a1 = new Admin("945678124v", "Sadun ", "Colombo", "077-1234567", "abc@ijse.lk", 5000.00f, 100000.00f);
        Admin a2 = new Admin("961710065v", "Dulanga ", "Galle", "077-1234567", "abc@ijse.lk", 5000.00f, 100000.00f);
        Admin a3 = new Admin("965678135v", "Nuwan ", "Matara", "077-1234567", "abc@ijse.lk", 5000.00f, 100000.00f);
        Admin a4 = new Admin("975675674v", "Amara ", "Kandy", "077-1234567", "abc@ijse.lk", 5000.00f, 100000.00f);
        Admin a5 = new Admin("941358124v", "Kamal ", "Jaffna", "077-1234567", "abc@ijse.lk", 5000.00f, 100000.00f);

        adminDB.add(a1);
        adminDB.add(a2);
        adminDB.add(a3);
        adminDB.add(a4);
        adminDB.add(a5);
    }

    public AdminService() {}

    public List<Admin> findAllStudents() {
        return adminDB;
    }

    public List<Admin> findStudents(String query) {
        List<Admin> result = new ArrayList<>();

        for (Admin admin : adminDB) {

            if (admin.getNic().contains(query) ||
                    admin.getFullName().contains(query)) {
                result.add(admin);
            }
        }
        return result;
    }

    public void saveStudent(Admin admin) {
        adminDB.add(admin);
    }

    public Admin findStudent(String id) {
        for (Admin admin : adminDB) {

            if (admin.getNic().equals(id)) {
                return admin;
            }
        }
        return null;
    }

    public void updateStudent(Admin admin) {
        Admin s = findStudent(admin.getNic());
        int index = adminDB.indexOf(s);
        adminDB.set(index, admin);
    }

    public void deleteStudent(String id) {
        Admin admin = findStudent(id);
        adminDB.remove(admin);
    }
}
