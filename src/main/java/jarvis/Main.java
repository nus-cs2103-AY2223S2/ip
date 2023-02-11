package jarvis;

import java.io.IOException;

import jarvis.ui.layout.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GUI class for Jarvis using FXML.
 */
public class Main extends Application {
    private final Jarvis jarvis = new Jarvis();

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add("/style/styles.css");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(400.0);
            primaryStage.setMinHeight(630.0);
            primaryStage.setTitle("Jarvis");
            fxmlLoader.<MainWindow>getController().setJarvis(jarvis);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
