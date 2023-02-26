package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

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
     * Method to create a DialogBox where the user's requests are displayed back to the user
     * @param text The text entered by the user
     * @param img The image that represents the user
     * @return The DialogBox where the user's requests are displayed back to the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox box = new DialogBox(text, img);
        box.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        return box;
    }


    /**
     * Method to create a DialogBox where the responses of the application are displayed back to the user
     * @param text The text that is the output from the application
     * @param img The image that represents the application (Duke)
     * @return The DialogBox where the responses of the application are displayed to the user
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(new Background(new BackgroundFill(Color.BISQUE,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        db.flip();
        return db;
    }
}