package drivers;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Contains message components for GUI
 */

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Returns an object to represent a message for GUI
     * @param l text to be displayed
     * @param iv image representing user who sent the message
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
}
