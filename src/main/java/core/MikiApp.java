package core;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shigure.Gui;
import shigure.fxcontrol.MainWindow;

/**
 * JavaFX launcher of the Miki project.
 */
public class MikiApp extends Application {
    @Override
    public void start(Stage stage) {
        assert stage != null : "Application stage should be non-null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Init.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Miki");
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.bindStage(stage);

            List<String> args = getParameters().getRaw();
            boolean hasAsciiOnly = false;
            boolean hasNoAutoload = false;
            for (int i = 0; i < args.size(); i++) {
                if (args.get(i).equals("--ascii-only")) {
                    hasAsciiOnly = true;
                }
                if (args.get(i).equals("--no-autoload")) {
                    hasNoAutoload = true;
                }
            }

            Gui gui = new Gui(mainWindow, stage, hasAsciiOnly);
            Miki miki = new Miki(gui, hasNoAutoload);

            fxmlLoader.<MainWindow>getController().setMiki(miki);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
