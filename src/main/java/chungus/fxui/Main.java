package chungus.fxui;

import java.io.IOException;

import chungus.Chungus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Chungus chungus;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        AnchorPane ap;

        try {
            ap = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(ap);
        primaryStage.setScene(scene);

        MainWindow mainWindow = fxmlLoader.<MainWindow>getController();

        if (chungus == null) {
            chungus = new Chungus(mainWindow, Chungus.DEFAULT_DB_PATH);
        }

        new Thread(() -> chungus.spin(() -> {
        }, () -> {
        })).start();

        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
        primaryStage.show();
    }

    @Override
    public void stop() {
        chungus.stop();
    }
}
