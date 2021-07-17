package Services;

import Model.Payment;
import Model.Student;
import Services.exception.DuplicateEntryException;
import Services.exception.FailedOperationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private static final File dbFile = new File("Payment.db");
    private static List<Payment> paymentList = new ArrayList<>();

    static {
        readDataFromFile();
    }

    public PaymentService() {}

    public void saveStudent(Payment payment) throws DuplicateEntryException, FailedOperationException {
        if(exitsPayment(payment.getCid())){
            throw new DuplicateEntryException();
        }
        try {
            paymentList.add(payment);
            writeDataToFile();
        }catch (FailedOperationException e){
            paymentList.remove(payment);
            throw e;
        }
    }


    public void updateStudent(Payment payment) throws FailedOperationException {
        Payment p = findStudent(payment.getCid());
        int index = paymentList.indexOf(p);
        try {
            paymentList.set(index, payment);
            writeDataToFile();
        }catch (FailedOperationException e){
            paymentList.set(index, p);
            throw e;
        }
    }

    public void deleteStudent(String id) throws FailedOperationException {
        Payment payment = findStudent(id);
        try {
            paymentList.remove(payment);
            writeDataToFile();
        }catch (FailedOperationException e){
            throw e;
        }


    }
    public List<Payment> findAllStudents() {
        return paymentList;
    }

    private boolean exitsPayment(String id){
        for(Payment payment: paymentList){
            if(payment.getCid().equals(id)){
                return true;
            }
        }
        return false;
    }
    public Payment findStudent(String id) {
        for (Payment payment : paymentList) {

            if (payment.getCid().equals(id)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> findStudents(String query) {
        List<Payment> result = new ArrayList<>();

        for (Payment payment : paymentList) {

            if (payment.getCid().contains(query) ||
                    payment.getCourseName().contains(query)) {
                result.add(payment);
            }
        }
        return result;
    }

    private void writeDataToFile() throws FailedOperationException {
        try(FileOutputStream fos = new FileOutputStream(dbFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(paymentList);
        }catch (Throwable e){
            e.printStackTrace();
            throw new FailedOperationException();
        }
    }

    private static void readDataFromFile(){

        if(!dbFile.exists()) return;


        try(FileInputStream fis = new FileInputStream(dbFile);
            ObjectInputStream ois = new ObjectInputStream(fis)){

            paymentList = (ArrayList<Payment>) ois.readObject();

        }catch (IOException | ClassNotFoundException e) {
            if(e instanceof EOFException){
                dbFile.delete();
            }else{
                e.printStackTrace();
            }
        }
    }
}
