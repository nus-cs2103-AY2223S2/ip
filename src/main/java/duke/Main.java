package duke;


import java.io.IOException;

import duke.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");
    private Image iconImage = new Image(this.getClass().getResourceAsStream("/images/retro-robot.png"));

    /**
     * This method runs the ui and scene for the bot.
     *
     * @param stage Sets the ui for the bot.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(iconImage);
            stage.setTitle("The Task Mechanic");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().sendStart();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
