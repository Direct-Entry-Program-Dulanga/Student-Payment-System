package Controller;

import Services.util.AppBarIcon;
import Services.util.MaterialUI;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginFormController {


    public TextField txtPassword;
    public TextField txtUserName;
    public Label lblError;

    public AnchorPane pneLogin;


    public void initialize() {
        MaterialUI.paintTextFields(txtPassword, txtUserName);
    }

    public void pneLogin_OnMouseClicked(MouseEvent mouseEvent) {
        if (txtUserName.getText().equals("User") && txtPassword.getText().equals("123")) {
            navigate("Student Payment System", "/View/HomeForm.fxml");
            lblError.setVisible(false);
            lblError.setText("Success");

        } else if (txtUserName.getText().equals("Admin") && txtPassword.getText().equals("123")) {
            navigate("Student Payment System", "/View/HomeAdminForm.fxml");
            lblError.setVisible(false);
            lblError.setText("Success");
        } else if (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()) {
            lblError.setVisible(true);
            lblError.setText("Please Enter your UserName & Password...");
        } else {
            txtUserName.clear();
            txtPassword.clear();
            lblError.setVisible(true);
            lblError.setText("Incorrect Username or Password");
        }
    }

    private void navigate(String title, String url) {
        MainFormController ctrl = (MainFormController) pneLogin.getScene().getUserData();
        ctrl.navigate(title, url, AppBarIcon.NAV_ICON_BACK, () ->
                ctrl.navigate("Student Payment System", "/View/LoginForm.fxml", AppBarIcon.NAV_ICON_BACK));
    }


}

