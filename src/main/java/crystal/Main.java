package crystal;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author Jeffry Lum-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * A GUI for Crystal using FXML.
 */
public class Main extends Application {


    private Crystal crystal = new Crystal();
    private Ui ui;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCrystal(crystal);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
