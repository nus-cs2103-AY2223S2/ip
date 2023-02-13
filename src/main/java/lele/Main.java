package lele;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.io.IOException;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Credits to @SPWwj for the idea of creating a separate class
 * to handle the GUI.
 */
public class Main extends Application {

    private Image icon = new Image("images/ShibaIcon.png");
    private final Lele lele = new Lele("./data/lele.txt");
    public Main() {}

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setLele(lele);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeApp() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> Platform.exit(), 2, TimeUnit.SECONDS);
    }
}
