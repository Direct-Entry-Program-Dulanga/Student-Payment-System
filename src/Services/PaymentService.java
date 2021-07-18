package Services;

import Model.Payment;
import Services.exception.DuplicateEntryException;
import Services.exception.FailedOperationException;
import Services.exception.NotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private static final File dbFile = new File("Payment.db");
    private static List<Payment> paymentList = new ArrayList<>();

    static {
        readDataFromFile();
    }

    public PaymentService() {
    }

    private static void readDataFromFile() {

        if (!dbFile.exists()) return;


        try (FileInputStream fis = new FileInputStream(dbFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            paymentList = (ArrayList<Payment>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof EOFException) {
                dbFile.delete();
            } else {
                e.printStackTrace();
            }
        }
    }

    public void savePayment(Payment payment) throws DuplicateEntryException, FailedOperationException {
        if (exitsPayment(payment.getCid())) {
            throw new DuplicateEntryException();
        }
        try {
            paymentList.add(payment);
            writeDataToFile();
        } catch (FailedOperationException e) {
            paymentList.remove(payment);
            throw e;
        }
    }

    public void updatePayment(Payment payment) throws NotFoundException, FailedOperationException {
        Payment p = findPayment(payment.getCid());
        int index = paymentList.indexOf(p);
        try {
            paymentList.set(index, payment);
            writeDataToFile();
        } catch (FailedOperationException e) {
            paymentList.set(index, p);
            throw e;
        }
    }

    public void deletePayment(String CID) throws NotFoundException, FailedOperationException {
        Payment payment = findPayment(CID);

        try{
            paymentList.remove(payment);
            writeDataToFile();
        }catch (FailedOperationException e){
            paymentList.add(payment);
            throw e;
        }
    }

    public List<Payment> findAllPayments() {
        return paymentList;
    }

    private boolean exitsPayment(String CID) {
        for (Payment payment : paymentList) {
            if (payment.getCid().equals(CID)) {
                return true;
            }
        }
        return false;
    }

    public Payment findPayment(String CID) {
        for (Payment payment : paymentList) {

            if (payment.getCid().equals(CID)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> findPayments(String query) {
        List<Payment> result = new ArrayList<>();

        for (Payment payment : paymentList) {

            if (payment.getCid().contains(query) ||
                    payment.getCourseName().contains(query) ||
                    payment.getRegister().contains(query) ||
                    payment.getPayment().contains(query)) {
                result.add(payment);
            }
        }
        return result;
    }

    private void writeDataToFile() throws FailedOperationException {
        try (FileOutputStream fos = new FileOutputStream(dbFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(paymentList);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new FailedOperationException();
        }
    }
}
