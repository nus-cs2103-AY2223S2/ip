package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * {@code DialogBox} class generates dialog for user and Duke himself
 */
public class DialogBox extends HBox {

    /**
     * dialog text wrapped around {@code Label} object
     */
    private Label text;

    /**
     * Picture to be used as icon for user or duke
     */
    private ImageView displayPicture;

    /**
     * Constructor method for Dialog Box
     * @param l Label object to wrap around text
     * @param iv image to be used as icon for user or duke
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
     * flips the node to the other side (for duke's case)
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Generates user's dialog
     * @param l Label object to wrap around text
     * @param iv image to be used as icon for user or duke
     * @return new DialogBox object for user
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Generates duke's dialog
     * @param l Label object to wrap around text
     * @param iv image to be used as icon for user or duke
     * @return new DialogBox object for duke
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
