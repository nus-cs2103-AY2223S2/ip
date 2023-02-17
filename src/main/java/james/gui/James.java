package james.gui;

import james.exception.JamesException;
import james.jamesbot.JamesBot;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




/**
 * Main file to run the program.
 */
public class James extends Application {
    @Override
    public void start(Stage stage) throws JamesException {
        JamesBot james = new JamesBot();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(James.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("JamesBot Chat");
            fxmlLoader.<MainWindow>getController().setJames(james);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
