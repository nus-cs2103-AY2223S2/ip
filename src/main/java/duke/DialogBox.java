package duke;

import java.io.IOException;
import java.util.Collections;

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

/**
 * Controller for DialogBox. A dialog box consisting of an ImageView to
 * represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image icon) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(icon);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> childNodes = FXCollections.observableArrayList(getChildren());
        Collections.reverse(childNodes);
        getChildren().setAll(childNodes);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets a dialog box for the user.
     * 
     * @param userText Dialog text.
     * @param userIcon User's icon.
     * @return Dialog box for the user.
     */
    public static DialogBox getUserDialog(String userText, Image userIcon) {
        return new DialogBox(userText, userIcon);
    }

    /**
     * Gets a dialog box for Duke.
     * 
     * @param dukeText Dialog text.
     * @param dukeIcon Duke's icon.
     * @return Dialog box for Duke.
     */
    public static DialogBox getDukeDialog(String dukeText, Image dukeIcon) {
        var db = new DialogBox(dukeText, dukeIcon);
        db.flip();
        return db;
    }
}
