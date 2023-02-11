package aqua;

import aqua.graphic.MainWindow;
import aqua.manager.LogicManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/** Main application class. */
public class MainApp extends Application {
    private static final String STAGE_TITLE = "Aqua";
    public static final Image ICON =
            new Image(MainApp.class.getResource("/icon/murasaki_tamanegi.png").toString());


    @Override
    public void start(Stage primaryStage) {
        MainWindow window = new MainWindow(new LogicManager());
        Scene scene = new Scene(window.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle(STAGE_TITLE);
        primaryStage.getIcons().add(ICON);
        primaryStage.show();
        window.start();
    }
}
