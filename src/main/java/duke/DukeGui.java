package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DukeGui extends Application {
    private static final String MAIN_WINDOW_RESOURCE_PATH = "/view/MainWindow.fxml";
    private static final String STYLESHEET_RESOURCE_PATH = "/view/style.css";
    private static final double MIN_WIDTH = 400;
    private static final double MIN_HEIGHT = 700;

    private final Bot bot = new Bot();

    @Override
    public void start(Stage stage) {
        try {
            bot.init();
        } catch (DukeException e) {
            // TODO: Should exit with error.
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeGui.class.getResource(MAIN_WINDOW_RESOURCE_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("iPman");
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);
            stage.setScene(scene);
            String styleSheet = this.getClass().getResource(STYLESHEET_RESOURCE_PATH).toExternalForm();
            scene.getStylesheets().addAll(styleSheet);
            fxmlLoader.<MainWindow>getController().setBot(bot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
