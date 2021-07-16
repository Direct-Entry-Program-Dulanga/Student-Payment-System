package Controller;

import Model.Student;
import Model.StudentTM;
import Services.StudentService;
import Services.util.MaterialUI;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class NewStudentFormController {

    public TextField txtStudentName;
    public TextField txtAddress;
    public TextField txtPhone;
    public TextField txtEmail;
    public RadioButton rbnDep;
    public RadioButton rbnGDse;
    public JFXButton btnSave;
    public AnchorPane root;
    public Label lblTitle;
    public TextField txtNIC;
    public Label lblNIC;
    public Label lblAddress;
    public Label lblContact;
    public Label lblEmail;
    public Label lblName;

    private final StudentService studentService = new StudentService();

    public void initialize() {
        MaterialUI.paintTextFields(txtStudentName, txtAddress, txtEmail, txtPhone, txtNIC);

//        setCourse();


        Platform.runLater(() -> {

            if (root.getUserData() != null) {
                StudentTM tm = (StudentTM) root.getUserData();
                Student student = studentService.findStudent(tm.getNic());

                txtNIC.setEditable(false);
                txtNIC.setText(student.getNic());
                txtStudentName.setText(student.getFullName());
                txtAddress.setText(student.getAddress());
                txtPhone.setText(student.getContact());
                txtEmail.setText(student.getEmail());

                btnSave.setText("UPDATE STUDENT");
                lblTitle.setText("Update Student");
            }
        });

    }


    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (!isValidated()) {
            return;
        } else {
            Student student = new Student(
                    txtNIC.getText(),
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtPhone.getText(),
                    txtEmail.getText());

            if (btnSave.getText().equals("ADD NEW STUDENT")) {
                studentService.saveStudent(student);
                txtNIC.clear();
                txtStudentName.clear();
                txtAddress.clear();
                txtPhone.clear();
                txtEmail.clear();
            } else {
                StudentTM tm = (StudentTM) root.getUserData();

                tm.setFullName(txtStudentName.getText());
                tm.setAddress(txtAddress.getText());
                studentService.updateStudent(student);
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
            lblContact.setText("(!) Invalid Contact");
            lblAddress.setVisible(false);
            txtPhone.requestFocus();
            return false;
        } else if (!email.matches("^\\w[\\w._]*\\w@(\\w?[\\w.])*[.]\\w{2,}")) {
            lblEmail.setText("(!) Invalid Contact");
            lblContact.setVisible(false);
            txtEmail.requestFocus();
            return false;
        } else {
            lblEmail.setVisible(false);
            lblNIC.setVisible(false);
            lblName.setVisible(false);
            lblAddress.setVisible(false);
            lblContact.setVisible(false);
            return true;
        }
    }
}
