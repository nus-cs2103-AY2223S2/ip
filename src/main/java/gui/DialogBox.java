package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An object to show a dialog box
 */
public class DialogBox extends HBox {
    private static double WIDTH = 100.0;
    private static double HEIGHT = 100.0;

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor
     * @param l a label object
     * @param iv an imageview object, which is the profile pic
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(WIDTH);
        displayPicture.setFitHeight(HEIGHT);

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
     * Returns a dialog object for user
     * @param l user text
     * @param iv an image view object to present text
     * @return a dialog object for user
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns a dialog object for duke
     * @param l duke text
     * @param iv an image view object to present text
     * @return a dialog object for duk e
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
