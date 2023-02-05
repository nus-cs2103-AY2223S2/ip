package duke.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A control that represents a dialog box by Duke consisting of a dialog to represent the text response
 * and an ImageView to represent the Duke image.
 */
public class DukeDialog extends HBox {
    /**
     * The text response of Duke.
     */
    @FXML
    private Label dialog;

    /**
     * The image of Duke.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DukeDialog.
     * @param text The text response of Duke.
     * @param img The image of Duke.
     */
    public DukeDialog(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/duke-dialog/DukeDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        displayPicture.setImage(img);
    }
}
