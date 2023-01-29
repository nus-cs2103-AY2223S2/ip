package aqua;

import aqua.graphic.MainWindow;
import aqua.manager.LogicManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/** Main application class. */
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainWindow window = new MainWindow(new LogicManager());
        Scene scene = new Scene(window.getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
        window.start();
    }
}
