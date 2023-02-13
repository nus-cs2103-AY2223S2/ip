package nemo;

import nemo.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Nemo using FXML.
 */
public class Main extends Application {

    private Nemo nemo = new Nemo();

    @Override
    public void start(Stage stage) {
        System.out.println("test");
        AnchorPane ap = new MainWindow(nemo);
        Scene scene = new Scene(ap);
        /*
        ap.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observable,
                                Scene oldValue, Scene newValue) {
                ap.prefWidthProperty().bind(newValue.widthProperty());
                ap.prefHeightProperty().bind(newValue.heightProperty());
            }
        });
        */
        stage.setScene(scene);
        stage.setTitle("Nemo - the task tracker");
        stage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("/images/icon.png")));
        stage.show();
    }
}
