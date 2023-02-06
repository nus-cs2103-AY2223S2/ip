package boo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Represents a GUI for the chatbot using FXML. */
public class Main extends Application {

    private float stageMinHeight = 600.0f;
    private float stageMinWidth = 800.0f;

    private String stageTitle = "Boo";

    private Boo boo = new Boo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorpane = fxmlLoader.load();
            Scene scene = new Scene(anchorpane);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setBoo(boo);
            mainWindow.setStage(stage);
            stage.show();

            stage.setTitle(stageTitle);
            stage.setResizable(false);
            stage.setMinHeight(stageMinHeight);
            stage.setMinWidth(stageMinWidth);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
