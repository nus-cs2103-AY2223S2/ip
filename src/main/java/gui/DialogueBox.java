package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * .
 */
public class DialogueBox extends HBox {
    /**
     * Instantiates a new DialogueBox.
     * @param p Text field to show.
     * @param img Image to add.
     * @param isUser Boolean variable to indicate type and decides alignment.
     */
    public DialogueBox(Label p, ImageView img, boolean isUser) {
        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(p, img);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(img, p);
        }
    }
}
