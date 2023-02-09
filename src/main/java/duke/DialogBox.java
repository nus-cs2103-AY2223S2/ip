package duke;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/** A JavaFX component representing a dialog box. */
public class DialogBox extends HBox {

    // user images
    private static final Image USER_IMAGE = new Image(DialogBox.class.getResourceAsStream("/images/red.png"));
    private static final Image DUKE_IMAGE = new Image(
            DialogBox.class.getResourceAsStream("/images/cyan.png"));

    // javafx component default settings
    private static final int FONT_SIZE = 16;
    private static final Font DEFAULT_FONT = new Font(FONT_SIZE);

    // javafx components
    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a DialogBox with a given text string and image.
     *
     * @param text  The message to be displayed
     * @param image The image to be displayed
     */
    private DialogBox(String text, Image image) {
        this.text = new Label(text);
        this.text.setFont(DialogBox.DEFAULT_FONT);
        this.text.setWrapText(true);

        this.displayPicture = new ImageView(image);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);
    }

    /**
     * Creates a DialogBox using the user's avatar.
     *
     * @param text The message to be displayed
     * @return The created DialogBox
     */
    public static DialogBox makeUserDialog(String text) {
        DialogBox dialogBox = new DialogBox("You said: " + text, DialogBox.USER_IMAGE);
        dialogBox.getChildren().addAll(dialogBox.displayPicture, dialogBox.text);
        dialogBox.setAlignment(Pos.TOP_LEFT);
        return dialogBox;
    }

    /**
     * Creates a DialogBox using Duke's avatar.
     *
     * @param text The message to be displayed
     * @return The created DialogBox
     */
    public static DialogBox makeDukeDialog(String text) {
        DialogBox dialogBox = new DialogBox(text, DialogBox.DUKE_IMAGE);
        dialogBox.getChildren().addAll(dialogBox.text, dialogBox.displayPicture);
        dialogBox.setAlignment(Pos.TOP_RIGHT);
        return dialogBox;
    }

}