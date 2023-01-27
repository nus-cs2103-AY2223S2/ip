package aqua;

import aqua.graphic.MainWindow;
import aqua.manager.LogicManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root = new MainWindow(new LogicManager()).getRoot();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
