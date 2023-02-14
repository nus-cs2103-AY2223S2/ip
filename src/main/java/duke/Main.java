package duke;

import java.io.File;
import java.io.IOException;

import duke.fxui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main process for starting the GUI for the chatbot.
 */
public class Main extends Application {
    public static final String FILE_PATH = "data" + File.separator + "tasks.txt";
    public static final String DUKE_CHATBOT = "Duke Chatbot";
    /**
     *
     */
    private final Duke duke = new Duke(FILE_PATH);

    /**
     * Creates a GUI with title of duke chatbot. It loads the main gui scene of
     * the chatbot and sets it up.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will
     *              not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            assert this.getClass().getResourceAsStream("/images/user.png") != null;
            assert this.getClass().getResourceAsStream("/images/duke.png") != null;

            FXMLLoader fxmlLoader =
                    new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vBox = fxmlLoader.load();
            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.setTitle(DUKE_CHATBOT);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
