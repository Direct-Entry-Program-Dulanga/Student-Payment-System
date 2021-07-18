package Controller;

import Services.util.AppBarIcon;
import com.jfoenix.controls.JFXRippler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomeAdminFormController {

    public JFXRippler rprAddNewStudent;
    public AnchorPane pneAddNewStudent;
    public JFXRippler rprSearchStudents;
    public AnchorPane pneSearchStudents;

    public AnchorPane pneAddCourse;
    public JFXRippler rprAddCourse;
    public AnchorPane pneSearchCourse;
    public JFXRippler rprSearchCourse;


    public void pneViewStudent_OnKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            rprSearchStudents.createManualRipple().run();
        }
    }

    public void pneViewStudent_OnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            navigate("View Student", "/View/SearchStudentForm.fxml");
        }
    }

    public void pneViewStudent_OnMouseClicked(MouseEvent mouseEvent) {
        navigate("Find Student", "/View/SearchStudentForm.fxml");
    }


    public void pneViewCourse_OnKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            rprSearchCourse.createManualRipple().run();
        }
    }

    public void pneViewCourse_OnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            navigate("View Courses", "/View/PaymentForm.fxml");
        }
    }

    public void pneViewCourse_OnMouseClicked(MouseEvent mouseEvent) {
        navigate("View Courses", "/View/PaymentForm.fxml");
    }


    public void pneAddCourse_OnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            rprAddCourse.createManualRipple().run();
        }
    }

    public void pneAddCourse_OnKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            navigate("Add Payment", "/View/PaymentEditForm.fxml");
        }
    }

    public void pneAddCourse_OnMouseClicked(MouseEvent mouseEvent) {
        navigate("Add Payment", "/View/PaymentEditForm.fxml");
    }


    public void pneAddStudent_OnKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            rprAddNewStudent.createManualRipple().run();
        }
    }

    public void pneAddStudent_OnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            navigate("Add Student", "/View/NewStudentFrom.fxml");
        }
    }

    public void pneAddStudent_OnMouseClicked(MouseEvent mouseEvent) {
        navigate("Add Student", "/View/NewStudentFrom.fxml");
    }

    private void navigate(String title, String url) {
        MainFormController ctrl = (MainFormController) pneSearchStudents.getScene().getUserData();
        ctrl.navigate(title, url, AppBarIcon.NAV_ICON_BACK, () ->
                ctrl.navigate("Student Payment System", "/View/HomeAdminForm.fxml", AppBarIcon.NAV_ICON_NONE));
    }
}
