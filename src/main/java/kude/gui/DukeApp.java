package kude.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import kude.Storage;
import kude.processor.Processor;


/**
 * Main JavaFX Application for Kude
 */
public class DukeApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        var gui = new Gui();
        var storage = new Storage("duke.txt");
        // TODO rework Ui interface
        var processor = new Processor(gui, storage);

        try {
            var fxmlLoader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
            fxmlLoader.<MainView>getController().setData(processor, gui);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
