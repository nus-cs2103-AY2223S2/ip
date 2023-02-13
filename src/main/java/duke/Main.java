package duke;

import java.io.IOException;

import duke.fx.FxUi;
import duke.fx.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * For GUI application.
 */
public class Main extends Application {
    private Duke duke;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/logo.jpg")));
            stage.setTitle("MEL");
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            FxUi ui = new FxUi(mainWindow);
            duke = new Duke(ui);
            mainWindow.setOnInputHandler(input -> {
                duke.handleInput(input);

                if (input.equals("bye")) {
                    stage.close();
                }
            });

            stage.show();
            duke.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
