package aqua;

import aqua.graphic.MainWindow;
import aqua.manager.LogicManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/** Main application class. */
public class MainApp extends Application {
    public static final Image ICON =
            new Image(MainApp.class.getResource("/icon/murasaki_tamanegi.png").toString());

    private static final String STAGE_TITLE = "Aqua";
    private static final double MIN_WIDTH = 616.0;
    private static final double MIN_HEIGHT = 439.0;


    @Override
    public void start(Stage primaryStage) {
        MainWindow window = new MainWindow(new LogicManager());
        Scene scene = new Scene(window.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle(STAGE_TITLE);
        primaryStage.getIcons().add(ICON);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.show();
        window.start();
    }
}
