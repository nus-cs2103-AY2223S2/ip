package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import duke.component.MainWindow;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            var url = Main.class.getResource("/view/MainWindow.fxml");
            System.out.println(url);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().getMinHeight();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Quit...");
    }
}
