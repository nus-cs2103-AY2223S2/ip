package io.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Dialogue box for user inputs and duke outputs.
 */
public class DialogueBox extends HBox {
    private static final double IMAGE_WIDTH = 100.0;
    private static final double IMAGE_HEIGHT = 100.0;

    private final Label label;
    private final ImageView displayPicture;

    /**
     * @param label Label with display text
     * @param displayPicture Image of dialogue source.
     */
    private DialogueBox(Label label, ImageView displayPicture) {
        this.label = label;
        this.displayPicture = displayPicture;

        this.label.setWrapText(true);
        this.displayPicture.setFitWidth(IMAGE_WIDTH);
        this.displayPicture.setFitHeight(IMAGE_HEIGHT);

        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(this.displayPicture, this.label);
    }

    public static DialogueBox of(String text, ImageView displayPicture) {
        return new DialogueBox(new Label(text), displayPicture);
    }
}
