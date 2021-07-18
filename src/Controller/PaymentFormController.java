package Controller;

import Model.Payment;
import Model.PaymentTM;
import Services.PaymentService;
import Services.exception.FailedOperationException;
import Services.exception.NotFoundException;
import Services.util.AppBarIcon;
import Services.util.MaterialUI;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class PaymentFormController {

    private final PaymentService paymentService = new PaymentService();
    public TableView<PaymentTM> tblAPayment;
    public TextField txtQuery;

    public void initialize() {
        MaterialUI.paintTextFields(txtQuery);
        tblAPayment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tblAPayment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblAPayment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("register"));
        tblAPayment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("payment"));
        TableColumn<PaymentTM, HBox> lastCol = (TableColumn<PaymentTM, HBox>) tblAPayment.getColumns().get(4);
        lastCol.setCellValueFactory(param -> {
            ImageView imgEdit = new ImageView("/View/assets/Edit.png");
            ImageView imgTrash = new ImageView("/View/assets/Trash.png");

            imgEdit.getStyleClass().add("action-icons");
            imgTrash.getStyleClass().add("action-icons");

            imgEdit.setOnMouseClicked(event -> updatePayment(param.getValue()));
            imgTrash.setOnMouseClicked(event -> deletePayment(param.getValue()));

            return new ReadOnlyObjectWrapper<>(new HBox(10, imgEdit, imgTrash));
        });

        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> loadAllPayment(newValue));

        loadAllPayment("");
    }

    private void loadAllPayment(String query) {
        tblAPayment.getItems().clear();

        for (Payment payment : paymentService.findPayments(query)) {
            tblAPayment.getItems().add(new PaymentTM(payment.getCid(), payment.getCourseName(), payment.getRegister(), payment.getPayment()));
            System.out.println(tblAPayment.getItems());
        }
    }

    private void deletePayment(PaymentTM tm) {
        try {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Course?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                paymentService.deletePayment(tm.getCid());
                tblAPayment.getItems().remove(tm);
            }
        } catch (FailedOperationException | NotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item", ButtonType.OK).show();
        }
    }

    private void updatePayment(PaymentTM tm) {
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
            secondaryStage.setTitle("Update Payment");
            ctrl.navigate("Update Payment", "/View/PaymentEditForm.fxml", AppBarIcon.NAV_ICON_NONE, null, tm);

            secondaryStage.showAndWait();
            tblAPayment.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
