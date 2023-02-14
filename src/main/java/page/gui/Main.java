package page.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import page.Page;

/**
 * A GUI for Page using FXML.
 */
public class Main extends Application {

    private Page page = new Page("data/questlog.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Page: Thy Medieval Assistant");
            fxmlLoader.<MainWindow>getController().setPage(page);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
