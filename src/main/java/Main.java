import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import treebot.TreeBot;
import ui.MainWindow;

public class Main extends Application {
    private TreeBot treeBot = new TreeBot("data/treebot.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTreeBot(treeBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
