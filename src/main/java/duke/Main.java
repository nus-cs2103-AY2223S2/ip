package duke;

import java.io.IOException;

import duke.ui.GraphicalUi;
import duke.ui.MainWindow;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main GUI application.
 */
public class Main extends Application {
    private static final String SAVE_DATA_FILE_PATH = "saveData.txt";
    private static final String SAVE_DATA_DIR_PATH = "./data";

    private Duke duke;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);

            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            Ui ui = new GraphicalUi(mainWindow);
            duke = new Duke(SAVE_DATA_DIR_PATH, SAVE_DATA_FILE_PATH, ui);
            mainWindow.setOnInputHandler(input -> {
                duke.handleInput(input);

                if (input.equals("bye")) {
                    primaryStage.close();
                }
            });

            primaryStage.show();
            primaryStage.setTitle("Persona Duke");

            duke.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
