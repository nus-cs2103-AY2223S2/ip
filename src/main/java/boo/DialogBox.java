package boo;

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

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and
 * a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    //Components inside the dialogue box
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Flips a dialog box such that the ImageView is on the left and text is on the right.
     * Differentiates between user input and bot's input.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a user dialog box.
     * @param label Text label in the user dialog box.
     * @param image Image of the user.
     * @return a user dialog box containing the required label and image.
     */
    public static DialogBox getUserDialog(String label, Image image) {
        return new DialogBox(label, image);
    }

    /**
     * Returns a bot dialog box which is flipped as compared to the user dialog box
     * @param label Text label in the bot dialog box.
     * @param image Image of the bot
     * @return a bot dialog box containing the required label and image.
     */
    public static DialogBox getBooDialog(String label, Image image) {
        DialogBox dukeDialogBox = new DialogBox(label, image);
        dukeDialogBox.flip();
        return dukeDialogBox;
    }

}
