package Controller;

import Model.Student;
import Model.StudentTM;
import Services.StudentService;
import Services.StudentServiceRedis;
import Services.exception.NotFoundException;
import Services.util.AppBarIcon;
import Services.util.MaterialUI;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class SearchStudentFormController {


    private StudentServiceRedis studentService = new StudentServiceRedis();
    public TextField txtQuery;
    public TableView<StudentTM> tblSearch;

    public void initialize() {
        MaterialUI.paintTextFields(txtQuery);
        tblSearch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblSearch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSearch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<StudentTM, HBox> lastCol = (TableColumn<StudentTM, HBox>) tblSearch.getColumns().get(3);

        lastCol.setCellValueFactory(param -> {
            ImageView imgEdit = new ImageView("/View/assets/Edit.png");
            ImageView imgTrash = new ImageView("/View/assets/Trash.png");

            imgEdit.getStyleClass().add("action-icons");
            imgTrash.getStyleClass().add("action-icons");

            imgEdit.setOnMouseClicked(event -> updateStudent(param.getValue()));
            imgTrash.setOnMouseClicked(event -> deleteStudent(param.getValue()));

            return new ReadOnlyObjectWrapper<>(new HBox(10, imgEdit, imgTrash));
        });

        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> loadAllStudents(newValue));

        loadAllStudents("");
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

    private void deleteStudent(StudentTM tm){
        try {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this student?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                studentService.deleteStudent(tm.getNic());
                tblSearch.getItems().remove(tm);
            }
        }catch (RuntimeException | NotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item", ButtonType.OK).show();
        }
    }

    private void loadAllStudents(String query){
        tblSearch.getItems().clear();
        for (Student student : studentService.findStudents(query)) {
            tblSearch.getItems().add(new StudentTM(student.getNic(), student.getName(), student.getAddress()));
        }
    }

    public void tblSearch_OnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            updateStudent(tblSearch.getSelectionModel().getSelectedItem());
        }
    }
}
