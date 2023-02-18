package meggy.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import meggy.Meggy;
import meggy.Util;

/** A chatbot GUI using FXML. */
public class MainApplication extends Application {
    /** The chatbot in use. */
    private final Meggy meggy = new Meggy(Util.DATA_FILE_PATH);

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
            stage.getIcons().add(GuiUtil.MEGGY_PROF_PIC);
            stage.setTitle(Meggy.class.getSimpleName());
            stage.setScene(scene);

            final MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setChatbot(meggy);
            mainWindow.setApDimProperty(ap.heightProperty(), ap.widthProperty());
            stage.show();
        } catch (IOException e) {
            // If FXML file IO causes an error, program must crash.
            throw new RuntimeException(e);
        }
    }
}
