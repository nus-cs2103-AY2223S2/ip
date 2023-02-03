package duke;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InitialWindow {

    private Duke duke = new Duke();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/view/MainWindow.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        System.out.println("fxmlLoader " + fxmlLoader);
        //fxmlLoader.<MainWindow>getController().setDuke(duke);
        stage.show();
    }


    /*
    @FXML
    Button button;
    */

    /*
    @FXML
    private void handleUserInput() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene2 = new Scene(ap);
            Stage stage = (Stage)((Node));
            scene2.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
            button.setOnAction(e -> ((Stage) scene2.getWindow()).setScene(scene2));

            //bp.getChildren().
            //Button button = scene2.lookup("#goButton");
            //stage.setScene(scene2);
            //stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            //stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

}
