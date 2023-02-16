package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Sets up the dialog GUI.
 */
public class DialogBox extends HBox {

    private Label pictureText;
    private ImageView displayPicture;
    private final Circle circle = new Circle(50, 50, 50);

    /**
     * Creates a Dialog box.
     *
     * @param l The text label
     * @param iv The user image
     */
    public DialogBox(Label l, ImageView iv) {

        displayPicture = iv;
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setStyle("-fx-padding: 20; -fx-border-radius: 20");
        displayPicture.setClip(circle);

        pictureText = new Label(l.getText(), displayPicture);
        pictureText.setWrapText(true);

        pictureText.setContentDisplay(ContentDisplay.RIGHT);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(pictureText);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        this.pictureText.setContentDisplay(ContentDisplay.LEFT);
        this.getChildren().setAll(pictureText);
    }

    /**
     * Creates a user dialog box.
     *
     * @param l The text label
     * @param iv The user image
     * @return The user dialog box.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates the Duke dialog box.
     *
     * @param l The text label
     * @param iv The user image
     * @return The Duke dialog box.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}