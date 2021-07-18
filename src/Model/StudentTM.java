package Model;

import java.io.Serializable;

public class StudentTM implements Serializable {

    private String nic;
    private String name;
    private String address;

    public StudentTM() {
    }

    public StudentTM(String nic, String name, String address) {
        this.setNic(nic);
        this.setName(name);
        this.setAddress(address);
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

    @Override
    public String toString() {
        return "StudentTM{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
