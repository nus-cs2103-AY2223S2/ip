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

/**
 * Controls the class for the InitialWindow.fxml file.
 * Provides the functionality for switching to MainWindow.
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @see Task
 * @since 11
 */
public class InitialWindow {

    private Duke duke = new Duke();

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Switches the scene to MainWindow when button is pressed.
     * @param event The ActionEvent that triggered this method.
     * @throws IOException If the FXMLLoader encounters an error while loading the MainWindow.fxml file.
     */
    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/view/MainWindow.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
