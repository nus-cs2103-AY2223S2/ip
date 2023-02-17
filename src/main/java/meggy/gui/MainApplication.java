package meggy.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import meggy.Meggy;

/** A chatbot GUI using FXML. */
public class MainApplication extends Application {

    private final Meggy meggy = new Meggy();

    /**
     * Run the GUI. Creates views using FXML.
     *
     * @throws RuntimeException If an FXML file {@link IOException} occurs.
     */
    @Override
    public void start(Stage stage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/view/MainWindow.fxml"));
            final AnchorPane ap = fxmlLoader.load();
            final Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setChatbot(meggy);
            mainWindow.setApDimProperty(ap.heightProperty(), ap.widthProperty());
            stage.show();
        } catch (IOException e) {
            // If FXML file IO causes an error, program must crash.
            throw new RuntimeException(e);
        }
    }
}
