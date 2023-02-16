package catbot;

import java.io.IOException;

import catbot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A GUI for CatBot using FXML.
 */
public class Main extends Application {

    private final CatBot catbot = new CatBot("./data/tasklist.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            BorderPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCatBot(catbot);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        catbot.close();
    }
}
