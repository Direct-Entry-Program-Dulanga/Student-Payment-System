package Model;

public class Admin {
    private String nic;
    private String fullName;
    private String address;
    private String contact;
    private String email;
    private float registerFee;
    private float paymentFee;

    public Admin() {
    }

    public Admin(String nic, String fullName, String address, String contact, String email, float registerFee, float paymentFee) {
        this.nic = nic;
        this.fullName = fullName;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.registerFee = registerFee;
        this.paymentFee = paymentFee;
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

    public float getRegisterFee() {
        return registerFee;
    }

    public void setRegisterFee(float registerFee) {
        this.registerFee = registerFee;
    }

    public float getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(float paymentFee) {
        this.paymentFee = paymentFee;
    }
}
