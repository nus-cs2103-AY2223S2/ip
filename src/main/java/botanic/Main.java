package botanic;

//@@author HmuuMyatMoe-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
//with minor modifications

import java.io.IOException;

import botanic.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A Graphical User Interface made using FXML.
 */
public class Main extends Application {

    private Botanic botanic;

    /**
     * Stages the primary stage onto the screen, this is the starting point of the application.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            botanic = new Botanic("./botanicData", "BotanicList.txt");
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("BOTanic");
            fxmlLoader.<MainWindow>getController().setBotanic(botanic);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the entire application and stores data of the tasks in the local hard drive
     * upon exiting the application.
     */
    @Override
    public void stop() {
        botanic.store();
    }
}
//@@author
