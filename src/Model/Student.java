package Model;

import java.util.HashMap;
import java.util.Map;

public class Student {

    private String nic;
    private String name;
    private String address;
    private String contact;
    private String email;

    public Student() {
    }

    public Student(String nic, String name, String address, String contact, String email) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
    }

    public static Student fromMap(String nic, Map<String, String> data) {
        return new Student(
                nic,
                data.get("name"),
                data.get("address"),
                data.get("contact"),
                data.get("email")
        );
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> toMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("address", address);
        map.put("contact", contact);
        map.put("email", email);
        return map;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
