package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static Stage stage;
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Main.stage = stage;
            scene.getStylesheets().add(Main.class.getResource("/style/stylesheet.css").toExternalForm());
            Main.stage.setScene(scene);
            Main.stage.setResizable(true);
            Main.stage.setTitle("Chopper Helpdesk");
            fxmlLoader.<MainWindow>getController().setDuke(this.duke);
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close application.
     */
    public static void closeStage() {
        Main.stage.close();
    }
}
