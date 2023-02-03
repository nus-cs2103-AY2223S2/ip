package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class InitialWindow {

    private Duke duke = new Duke();

    @FXML
    Button button;

    @FXML
    private void handleUserInput() {

        Scene scene = button.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene2 = new Scene(ap);
            scene2.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
            button.setOnAction(e -> stage.setScene(scene2));

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

            stage.setScene(scene2);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}
