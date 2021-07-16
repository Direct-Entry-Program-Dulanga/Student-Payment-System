package Controller;

import Model.Admin;
import Model.AdminTM;
import Services.AdminService;
import Services.util.MaterialUI;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AdminStudentFormController {
    private final AdminService adminService = new AdminService();
    public Label lblTitle;
    public TextField txtNIC;
    public TextField txtStudentName;
    public TextField txtAddress;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtRegister;
    public TextField txtPayment;
    public RadioButton rbnGDse;
    public RadioButton rbnDep;
    public ToggleGroup course;
    public JFXButton btnSave;
    public AnchorPane root;
    public Label lblNIC;
    public Label lblName;
    public Label lblAddress;
    public Label lblPhone;
    public Label lblEmail;
    public Label lblRegister;
    public Label lblPayment;


    public void initialize() {
        MaterialUI.paintTextFields(txtStudentName, txtAddress, txtEmail, txtPhone, txtNIC, txtPayment, txtRegister);
//        setCourse();

        Platform.runLater(() -> {

            if (root.getUserData() != null) {
                AdminTM tm = (AdminTM) root.getUserData();
                Admin admin = adminService.findStudent(tm.getNic());

                txtNIC.setText(admin.getNic());
                txtStudentName.setText(admin.getFullName());
                txtAddress.setText(admin.getAddress());
                txtPhone.setText(admin.getContact());
                txtEmail.setText(admin.getEmail());
                txtRegister.setText(String.valueOf(admin.getRegisterFee()));
                txtPayment.setText(String.valueOf(admin.getPaymentFee()));
                btnSave.setText("UPDATE STUDENT");
                lblTitle.setText("Update Student");

            }
        });

    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        if (!isValidated()) {
            return;
        } else {
            Admin admin = new Admin(
                    txtNIC.getText(),
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    Float.parseFloat(txtRegister.getText()),
                    Float.parseFloat(txtPayment.getText()));

            if (btnSave.getText().equals("UPDATE STUDENT")) {
                adminService.saveStudent(admin);
            } else {
                AdminTM tm = (AdminTM) root.getUserData();
                tm.setFullName(txtStudentName.getText());
                tm.setAddress(txtAddress.getText());
                adminService.updateStudent(admin);
            }
            new Alert(Alert.AlertType.NONE, "Student has been saved successfully", ButtonType.OK).show();
        }
    }


    private boolean isValidated() {
        String nic = txtNIC.getText();
        String fullName = txtStudentName.getText();
        String address = txtAddress.getText();
        String contact = txtPhone.getText();
        String email = txtEmail.getText();
        String register = txtRegister.getText();
        String payment = txtPayment.getText();

//        Student tm = (Student) root.getUserData();
//        Student st = studentService.findStudent(tm.getNic());

//        if (!nic.equals(st.getNic())) {
//            lblNIC.setText("(!) NIC Already Exists");
//            txtNIC.requestFocus();
//            System.out.println("nic: "+ nic + ", studentTM nic:"+ st.getNic());
////            MaterialUIError.paintTextFields(txtNIC);
//            return false;
//    }
        if (!(nic.length() == 10 && nic.matches("\\d{9}[vV]"))) {
            lblNIC.setText("(!) Invalid NIC");
            return false;
        } else if (!(fullName.trim().length() >= 3 || fullName.matches("[A-za-z\\s]|[.]"))) {
            lblName.setText("(!) Invalid User Name");
            lblNIC.setVisible(false);
            txtStudentName.requestFocus();
            return false;
        } else if (!(address.trim().length() >= 4 && address.matches("^[a-zA-Z0-9\\s,-/\\\\]+$"))) {
            lblAddress.setText("(!) Invalid Address");
            lblName.setVisible(false);
            txtAddress.requestFocus();
            return false;
        } else if (!contact.matches("\\d{3}-\\d{7}")) {
            lblPhone.setText("(!) Invalid Contact");
            lblAddress.setVisible(false);
            txtPhone.requestFocus();
            return false;
        } else if (!email.matches("^\\w[\\w._]*\\w@(\\w?[\\w.])*[.]\\w{2,}")) {
            lblEmail.setText("(!) Invalid Email");
            lblPhone.setVisible(false);
            txtEmail.requestFocus();
            return false;
        } else if (!register.matches("^\\d{3,}[.]\\d$")) {
            lblRegister.setText("(!) Invalid Register Payment");
            lblEmail.setVisible(false);
            txtRegister.requestFocus();
            return false;
        } else if (!payment.matches("^\\d{3,}[.]\\d$")) {
            lblPayment.setText("(!) Invalid Full Payment");
            lblRegister.setVisible(false);
            txtPayment.requestFocus();
            return false;
        } else {
            lblEmail.setVisible(false);
            lblNIC.setVisible(false);
            lblName.setVisible(false);
            lblAddress.setVisible(false);
            lblEmail.setVisible(false);
            lblPayment.setVisible(false);
            return true;
        }
    }
}
