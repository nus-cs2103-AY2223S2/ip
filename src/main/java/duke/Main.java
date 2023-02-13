package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        System.out.println("test");
        AnchorPane ap = new MainWindow(duke);
        Scene scene = new Scene(ap);
//        ap.sceneProperty().addListener(new ChangeListener<Scene>() {
//            @Override
//            public void changed(ObservableValue<? extends Scene> observable,
//                                Scene oldValue, Scene newValue) {
//                ap.prefWidthProperty().bind(newValue.widthProperty());
//                ap.prefHeightProperty().bind(newValue.heightProperty());
//            }
//        });

        stage.setScene(scene);
        stage.show();
    }
}
