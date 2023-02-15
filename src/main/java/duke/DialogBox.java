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
import javafx.scene.layout.Region;

/**
 * A class that represents the container for the user's input or for duke's response,
 * it consists of an ImageView representing the user/duke icon and a Label containing
 * the user's input or duke's response.
 * This container is to be displayed onto the screen.
 */
public class DialogBox extends HBox {
    /**
     * The label containing a string representing the user input or duke's response.
     */
    @FXML
    private Label dialog;
    /**
     * The icon to be displayed, representing the user's icon or the duke's icon.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * Instantiates DialogBox.
     *
     * @param text A string that represents user's input or duke's response.
     * @param img The user's or duke's icon.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        dialog.setMinHeight(Region.USE_PREF_SIZE);
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
     * Instantiates DialogBox where the ImageView is on the right and text on the left,
     * which represents the user's input.
     *
     * @param text The user's input.
     * @param img The user's icon.
     * @return A DialogBox representing the user's input message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Instantiates DialogBox where the ImageView is on the left and text on the right,
     * which represents the duke's response.
     *
     * @param text Duke's response.
     * @param img Duke's icon.
     * @return A DialogBox representing duke's response message.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
