package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a Dialog box that will appear in the GUI, containing an image and the text message.
 * It is a custom Control.
 */
public class DialogBox extends HBox {

    //Controls in the dialogue box
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips a dialog box such that the ImageView is on the left and text is on the right.
     * Differentiates between user input and bot's input.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a user dialog box.
     * @param label Text label in the user dialog box.
     * @param imageView Image of the user.
     * @return a user dialog box containing the required label and image.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Returns a bot dialog box which is flipped as compared to the user dialog box
     * @param label Text label in the bot dialog box.
     * @param imageView Image of the bot
     * @return a bot dialog box containing the required label and image.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        DialogBox dukeDialogBox = new DialogBox(label, imageView);
        dukeDialogBox.flip();
        return dukeDialogBox;
    }

}
