package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * A custom control using FXML that handles the Dialogue Box in the GUI.
 * It represents a Dialogue Box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * Reused from https://se-education.org/guides/tutorials/javaFx.html
*/
public class DialogBox extends HBox {

    private static final float WRAPPING_WIDTH = 250;
    private static final float IMAGE_LENGTH = 70;
    private static final float ROUNDED_CORNERS_LENGTH = 25;    

    private static final Color DUKE_COLOR = Color.CORNFLOWERBLUE;
    private static final Color USER_COLOR = Color.CRIMSON;

    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getClassLoader().getResource("view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setWrappingWidth(DialogBox.WRAPPING_WIDTH);

        displayPicture.setImage(img);
        DialogBox.roundCornersOfImage(displayPicture);
    }

    private static void roundCornersOfImage(ImageView initialImage) {
        Rectangle roundRect = new Rectangle(0, 0, DialogBox.IMAGE_LENGTH, DialogBox.IMAGE_LENGTH);
        roundRect.setArcHeight(DialogBox.ROUNDED_CORNERS_LENGTH);
        roundRect.setArcWidth(DialogBox.ROUNDED_CORNERS_LENGTH);
        initialImage.setClip(roundRect);
    }

    /**
     * Sets the font color of the
     * text component.
     *
     * @param color Color to set font to.
     */
    public void setFontColor(Color color) {
        this.dialog.setFill(color);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox that belongs to the user.
     * The ImageView will be oriented to the right of
     * the DialogBox.
     *
     * @param text Text to place in user DialogBox.
     * @param img Image to place in user DialogBox.
     * @return User DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setFontColor(DialogBox.USER_COLOR);
        return db;
    }

    /**
     * Returns a DialogBox that belongs to Duke.
     * The ImageView will be oriented to the left of
     * the DialogBox.
     *
     * @param text Text to place in duke DialogBox.
     * @param img Image to place in duke DialogBox.
     * @return Duke DialogBox.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setFontColor(DialogBox.DUKE_COLOR);
        db.flip();
        return db;
    }

}
