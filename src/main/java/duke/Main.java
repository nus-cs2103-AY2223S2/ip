package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/InitialWindow.fxml"));
            //AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(fxmlLoader.load());
            //scene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());

            //FXMLLoader fxmlLoaderInitial = new FXMLLoader(Main.class.getResource("/view/InitialWindow.fxml"));
            //BorderPane bp = fxmlLoaderInitial.load();
            //Scene scene2 = new Scene(fxmlLoaderInitial.load());
            //bp.getChildren().
            //Button button = scene2.lookup("#goButton");



            /*
            Label label1 = new Label("Welcome to Manchester United");
            Button button = new Button();
            button.setOnAction(e -> stage.setScene(scene));
            VBox layout1 = new VBox(20);
            layout1.getChildren().addAll(label1, button);
            Scene scene2 = new Scene(layout1, 200, 200);
             */

            stage.setScene(scene);
            stage.setTitle("Duke");
            //fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
