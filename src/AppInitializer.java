import Controller.MainFormController;
import Services.util.AppBarIcon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            JedisClient.getInstance().getClient().shutdown();
        }));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                new Alert(Alert.AlertType.ERROR, "Something will terribly wrong").show();
                System.exit(1);
            }
        });

        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/View/SplashScreenForm.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
