package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Creates a custom user and bot dialog chat in GUI.
 * It combines an Image and a text into a HBox.
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @since 11
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new instance of the DialogBox class with the specified text and image.
     *
     * @param text The text to be displayed in the label
     * @param img The image to be displayed in the ImageView
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initStyle(text, img);
    }

    /**
     * Gets the label that displays the text.
     *
     * @return The label that displays the text
     */
    Label getDialog() {
        return dialog;
    }

    /**
     * Gets the ImageView that displays the speaker's picture.
     *
     * @return The ImageView that displays the speaker's picture
     */
    ImageView getDisplayPicture() {
        return displayPicture;
    }

    /**
     * Initializes the style of the dialog box.
     *
     * @param text The text to be displayed in the label
     * @param img The image to be displayed in the ImageView
     */
    void initStyle(String text, Image img) {
        dialog.setText(text);
        dialog.getStyleClass().add("round-lbl");
        displayPicture.setClip(new Circle(70, 43, 40));
        displayPicture.setImage(img);
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(15.0);
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
     * Returns a DialogBox object representing a user's dialog.
     * @param text the text of the dialog
     * @param img the image of the user's face
     * @return a DialogBox object representing a user's dialog
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        userDialog.getStyleClass().add("user-background");
        return userDialog;
        //return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox object representing Duke's dialog.
     * @param text the text of the dialog
     * @param img the image of Duke's face
     * @return a DialogBox object representing Duke's dialog
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
