package duke.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * A control that represents a dialog box by the user consisting of a dialog to represent
 * the user's text input.
 */
public class UserDialog extends StackPane {
    /**
     * The user's text input.
     */
    @FXML
    private Label dialog;

    /**
     * Constructor for UserDialog.
     * @param text The user's text input.
     */
    public UserDialog(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/user-dialog/UserDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }
}
