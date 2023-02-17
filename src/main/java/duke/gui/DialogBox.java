package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Abstraction for a single HBox to display messages.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPic;

    /**
     * Constructor for DialogBox class.
     * @param text The message text to be displayed.
     * @param displayPic An image of the "person" to be shown alongside the message.
     */
    public DialogBox(Label text, ImageView displayPic) {
        this.text = text;
        this.displayPic = displayPic;

        text.setWrapText(true);
        displayPic.setFitWidth(80.0);
        displayPic.setFitHeight(80.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPic);
    }

    /**
     * Flips the dialog box horizontally.
     */
    private void flip() {
        assert this.getAlignment().equals(Pos.TOP_LEFT);
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a DialogBox aligned for the user.
     * @param text The message text to be displayed.
     * @param displayPic An image of the "person" to be shown alongside the message.
     * @return
     */
    public static DialogBox getUserDialog(Label text, ImageView displayPic) {
        return new DialogBox(text, displayPic);
    }

    /**
     * Creates a DialogBox aligned for the user.
     * @param text The message text to be displayed.
     * @param displayPic An image of the "person" to be shown alongside the message.
     * @return
     */
    public static DialogBox getDukeDialog(Label text, ImageView displayPic) {
        var db = new DialogBox(text, displayPic);
        db.flip();
        return db;
    }
}
