package chattime;

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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

// @@author Jeffry Lum-reused
// Reused from Guides for SE Student Project- Java FX Tutorial part 4
// with minor modification

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label username;
    @FXML
    private VBox userid;

    /**
     * Creates dialog box object to show box or user's reply messages.
     *
     * @param text The text output.
     * @param img The profile picture of the content owner.
     */
    public DialogBox(String text, Image img, String name) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        username.setText(name);
        username.getStyleClass().add("user");
        username.setAlignment(Pos.CENTER);
        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates dialog box for user message.
     *
     * @param text The user's input.
     * @param img The user's profile.
     * @return The dialog box with user's message and user's profile.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialogBox = new DialogBox(text, img, "User");
        userDialogBox.setAlignment(Pos.CENTER_RIGHT);
        userDialogBox.dialog.getStyleClass().add("user-dialog");
        return userDialogBox;
    }

    /**
     * Creates dialog box for bot message.
     *
     * @param text The bot input.
     * @param img The bot profile.
     * @return The dialog box with bot message and bot profile.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        var db = new DialogBox(text, img, "Chattime");
        db.setAlignment(Pos.CENTER_LEFT);
        db.dialog.getStyleClass().add("bot-dialog");
        db.flip();
        return db;
    }
}

// @@author Jeffry Lum-reused
