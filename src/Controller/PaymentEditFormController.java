package Controller;

import Model.Payment;
import Model.PaymentTM;
import Services.PaymentService;
import Services.PaymentServiceRedis;
import Services.exception.DuplicateEntryException;
import Services.exception.NotFoundException;
import Services.util.MaterialUI;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class PaymentEditFormController {
    public AnchorPane root;
    public TextField txtCourseID;
    public Label lblCID;
    public TextField txtCourseName;
    public Label lblCName;
    public TextField txtRegister;
    public Label lblRegister;
    public TextField txtPayment;
    public Label lblPayment;
    public JFXButton btnSave;
    public Label lblTitle;

    private final PaymentServiceRedis paymentService = new PaymentServiceRedis();


    public void initialize(){
        MaterialUI.paintTextFields(txtCourseID, txtCourseName, txtRegister, txtPayment);
//        setCourse();

        Platform.runLater(()->{

            if (root.getUserData() != null){
                PaymentTM tm = (PaymentTM) root.getUserData();
                Payment payment = null;
                try {
                    payment = paymentService.findPayment(tm.getCid());
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }

                txtCourseID.setText(payment.getCid());
                txtCourseName.setText(payment.getCourseName());
                txtRegister.setText(payment.getRegister());
                txtPayment.setText(payment.getPayment());
                btnSave.setText("UPDATE COURSE");
                lblTitle.setText("Update Course");

            }
        });

    }

    public void btnSave_OnAction(ActionEvent actionEvent) throws NotFoundException, DuplicateEntryException {
        if (!isValidated()) {
            return;
        } else {
            Payment payment = new Payment(
                    txtCourseID.getText(),
                    txtCourseName.getText(),
                    txtRegister.getText(),
                    txtPayment.getText());

            if (btnSave.getText().equals("ADD NEW STUDENT")) {
                paymentService.savePayment(payment);
                txtPayment.clear();
                txtCourseID.clear();
                txtCourseName.clear();
                txtRegister.clear();
            } else {
                PaymentTM tm = (PaymentTM) root.getUserData();

                tm.setCourseName(txtCourseName.getText());
                tm.setCid(txtCourseID.getText());
                paymentService.updatePayment(payment);
            }
            new Alert(Alert.AlertType.NONE, "Student has been saved successfully", ButtonType.OK).show();
        }
    }


    private boolean isValidated() {
        String cid = txtCourseID.getText();
        String CName = txtCourseName.getText();
        String register = txtRegister.getText();
        String payment = txtPayment.getText();

        if(!cid.matches("^[C][-]\\d{2,}")){
            lblCID.setText("(!) Invalid NIC");
            return false;
        }else if (!(CName.trim().length() >= 3 || CName.matches("[A-za-z\\s]|[.]"))) {
            lblCName.setText("(!) Invalid User Name");
            lblCID.setVisible(false);
            txtCourseID.requestFocus();
            return false;
        }else if(!register.matches("^\\d{3,}[.]\\d{2,}$")) {
            lblRegister.setText("(!) Invalid Register Payment");
            lblRegister.setVisible(false);
            txtRegister.requestFocus();
            return false;
        }else if(!payment.matches("^\\d{3,}[.]\\d{2,}$")){
            lblPayment.setText("(!) Invalid Full Payment");
            lblRegister.setVisible(false);
            txtPayment.requestFocus();
            return false;
        } else {
            lblCID.setVisible(false);
            lblCName.setVisible(false);
            lblRegister.setVisible(false);
            lblPayment.setVisible(false);
            return true;
        }
    }
}
