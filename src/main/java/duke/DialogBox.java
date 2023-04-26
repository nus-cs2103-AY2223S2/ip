package duke;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param dialog The dialog to be displayed.
     * @param displayPicture The image to be displayed.
     */
    public DialogBox(Label dialog, ImageView displayPicture) {
        this.dialog = dialog;
        this.displayPicture = displayPicture;

        final Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        dialog.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        dialog.setPadding(new Insets(5, 10, 0, 10));
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(dialog, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog box with the user's input.
     *
     * @param text The user's input.
     * @param img The user's image.
     * @return A dialog box with the user's input.
     */
    public static DialogBox getUserDialog(Label text, ImageView img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a dialog box with Duke's response.
     *
     * @param text Duke's response.
     * @param img Duke's image.
     * @return A dialog box with Duke's response.
     */
    public static DialogBox getDukeDialog(Label text, ImageView img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
