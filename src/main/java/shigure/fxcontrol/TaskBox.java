package shigure.fxcontrol;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * A task display box for the Miki JavaFX GUI.
 */
public class TaskBox extends HBox {
    @FXML
    private Label task;

    /**
     * Creates a new <code>TaskBox</code>.
     *
     * @param text text contents of the <code>TaskBox</code>.
     */
    public TaskBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/TaskBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            task.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
