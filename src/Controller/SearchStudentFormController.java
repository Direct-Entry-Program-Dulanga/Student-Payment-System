package Controller;

import Model.Student;
import Model.StudentTM;
import Services.StudentService;
import Services.util.AppBarIcon;
import Services.util.MaterialUI;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SearchStudentFormController {


    private final StudentService studentService = new StudentService();
    public TextField txtQuery;
    public TableView<StudentTM> tblSearch;

    public void initialize() {
        MaterialUI.paintTextFields(txtQuery);
        tblSearch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblSearch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblSearch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<StudentTM, HBox> lastCol = (TableColumn<StudentTM, HBox>) tblSearch.getColumns().get(3);

        lastCol.setCellValueFactory(param -> {
            ImageView imgEdit = new ImageView("/View/assets/Edit.png");

            imgEdit.getStyleClass().add("action-icons");

            imgEdit.setOnMouseClicked(event -> updateStudent(param.getValue()));

            return new ReadOnlyObjectWrapper<>(new HBox(5, imgEdit));
        });

        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> loadAllStudents(newValue));

        loadAllStudents("");
    }

    private void loadAllStudents(String query) {
        tblSearch.getItems().clear();

        for (Student student : studentService.findStudents(query)) {
            tblSearch.getItems().add(new StudentTM(
                    student.getNic(),
                    student.getFullName(),
                    student.getAddress()
            ));
        }
    }

    private void updateStudent(StudentTM tm) {
        try {
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/MainForm.fxml"));
            Scene secondaryScene = new Scene(loader.load());
            MainFormController ctrl = loader.getController();

            secondaryStage.setScene(secondaryScene);
            secondaryScene.setFill(Color.TRANSPARENT);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(txtQuery.getScene().getWindow());
            secondaryStage.setTitle("Update Student");
            ctrl.navigate("Update Student", "/View/NewStudentFrom.fxml", AppBarIcon.NAV_ICON_NONE, null, tm);

            secondaryStage.showAndWait();
            tblSearch.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
