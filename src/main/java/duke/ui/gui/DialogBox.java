package duke.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

/**
 * Controller for DialogBox. Represents a dialog box consisting of an ImageView to represent the speaker's face and a
 * label containing the text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(message);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns a dialog box with a message that appears to be spoken by the user.
     *
     * @param message The message.
     * @param img The image of the user.
     * @return A dialog box with a message that appears to be spoken by the user.
     */
    public static DialogBox getUserDialog(String message, Image img) {
        return new DialogBox(message, img);
    }

    /**
     * Creates and returns a dialog box with a message that appears to be spoken by Duke.
     *
     * @param message The message.
     * @param img The image of Duke.
     * @return A dialog box with a message that appears to be spoken by Duke.
     */
    public static DialogBox getDukeDialog(String message, Image img) {
        var db = new DialogBox(message, img);
        db.flip();
        return db;
    }
}
