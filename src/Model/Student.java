package Model;

public class Student {

    private String nic;
    private String fullName;
    private String address;
    private String contact;
    private String email;

    public Student() {
    }

    public Student(String nic, String fullName, String address, String contact, String email) {
        this.nic = nic;
        this.fullName = fullName;
        this.address = address;
        this.contact = contact;
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
}
