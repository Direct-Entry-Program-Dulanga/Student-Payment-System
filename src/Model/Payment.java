package Model;

import java.util.HashMap;
import java.util.Map;

public class Payment {
    private String cid;
    private String courseName;
    private String register;
    private String payment;

    public Payment() {
    }

    public Payment(String cid, String courseName, String register, String payment) {
        this.cid = cid;
        this.courseName = courseName;
        this.register = register;
        this.payment = payment;
    }

    public static Payment fromMap(String cid, Map<String, String> data) {
        return new Payment(
                cid,
                data.get("courseName"),
                data.get("register"),
                data.get("payment")
        );
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

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Map<String, String> toMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("courseName", courseName);
        map.put("register", register);
        map.put("payment", payment);
        return map;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "cid='" + cid + '\'' +
                ", courseName='" + courseName + '\'' +
                ", register='" + register + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}
