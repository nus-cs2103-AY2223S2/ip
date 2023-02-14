package duke.ui;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(25, 25, 25));
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
     * Returns the dialog for user.
     *
     * @param text dialog text
     * @param img dialog user profile picture
     * @return a DialogBox
     * @see Image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns the dialog for duke.
     *
     * @param text dialog text
     * @param img dialog user profile picture
     * @return a DialogBox
     * @see Image
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
        return db;
    }

    /**
     * Returns a warning dialog for duke.
     *
     * @param text dialog text
     * @param img dialog user profile picture
     * @return a DialogBox
     * @see Image
     */
    public static Node getDukeWarningDialog(String text, Image img) {
        var db = getDukeDialog(text, img);
        db.dialog.setTextFill(Color.ORANGE);;
        return db;
    }

    /**
     * Returns an error dialog for duke.
     *
     * @param text dialog text
     * @param img dialog user profile picture
     * @return a DialogBox
     * @see Image
     */
    public static Node getDukeErrorDialog(String text, Image img) {
        var db = getDukeDialog(text, img);
        db.dialog.setTextFill(Color.RED);;
        return db;
    }
}
