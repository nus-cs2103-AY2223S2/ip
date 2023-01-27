package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class that handles the Dialogue Box in the GUI.
 * Reused from https://se-education.org/guides/tutorials/javaFx.html
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the text Label l
     * and the ImageView iv.
     *
     * @param l Label to be placed in DialogBox.
     * @param iv ImageView to be placed in DialogBox.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox that belongs to the user.
     * The ImageView will be oriented to the right of
     * the DialogBox.
     *
     * @param l Label to place in user DialogBox.
     * @param iv ImageView to place in user DialogBox.
     * @return User DialogBox.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns a DialogBox that belongs to Duke.
     * The ImageView will be oriented to the left of
     * the DialogBox.
     *
     * @param l Label to place in duke DialogBox.
     * @param iv ImageView to place in duke DialogBox.
     * @return Duke DialogBox.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
