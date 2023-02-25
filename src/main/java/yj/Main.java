package yj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

    /**
     * A GUI for Duke using FXML.
     */
    public class Main extends Application {

        private YJ yj = new YJ();

        @Override
        public void start(Stage stage) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
                AnchorPane ap = fxmlLoader.load();
                assert ap != null : "AnchorPane is null";
                Scene scene = new Scene(ap);
                assert scene != null : "Scene is null";
                stage.setScene(scene);
                assert yj != null : "YJ is null";
                fxmlLoader.<MainWindow>getController().setYJ(yj);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
