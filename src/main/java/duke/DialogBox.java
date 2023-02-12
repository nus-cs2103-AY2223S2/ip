package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

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

    /**
     * Creates a DialogBox object with the respective String text and Image img given.
     *
     * @param text String message belonging to the DialogBox object
     * @param img Image belonging to the DialogBox object
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

        // @@author Jai2501-reused
        // Reused from https://github.com/Jai2501/ip/blob/master/src/main/java/retriever/DialogBox.java
        // with minor modifications and mainly the portion where the author did padding.
        dialog.setText(text);
        dialog.setPadding(new Insets(10, 10, 10 , 10));
        dialog.setStyle("-fx-background-color: #ffffa2; -fx-background-insets: 5; -fx-background-radius: 15;");

        // @@author czhi-bin-reused
        // Reused from https://github.com/czhi-bin/ip/blob/master/src/main/java/angrybot/DialogBox.java
        // with minor modifications and mainly the portion where the author did the clipping of image to a circle.
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(45, 45, 40));
        displayPicture.setStyle("-fx-border-image-insets: 10;");
        // @@author MrTwit99
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
     * Returns a DialogBox object that contains the user dialog.
     *
     * @param text String message by the User.
     * @param img Image belonging to the User.
     * @return DialogBox object containing the user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox object that contains the system (Duke) dialog.
     *
     * @param text String message by the system (Duke).
     * @param img Image belonging to the system (Duke).
     * @return DialogBox object containing the system (Duke) dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
