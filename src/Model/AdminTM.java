package Model;

public class AdminTM {

    private String nic;
    private String fullName;
    private String address;
    private float registerFee;
    private float paymentFee;

    public AdminTM() {
    }

    public AdminTM(String nic, String fullName, String address, float registerFee, float paymentFee) {
        this.nic = nic;
        this.fullName = fullName;
        this.address = address;
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
