package Model;

public class Payment {
    private String cid;
    private String courseName;
    private float register;
    private float payment;

    public Payment() {
    }

    public Payment(String cid, String courseName, float register, float payment) {
        this.cid = cid;
        this.courseName = courseName;
        this.register = register;
        this.payment = payment;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getRegister() {
        return register;
    }

    public void setRegister(float register) {
        this.register = register;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }
}
