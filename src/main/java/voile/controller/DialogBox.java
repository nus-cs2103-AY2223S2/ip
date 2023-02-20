package voile.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Controller for DialogBox.
 */
public class DialogBox extends HBox implements FxmlComponent {

    @FXML
    private Text dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new {@code DialogBox} from the given string and image.
     *
     * @param text the given string
     * @param image the given image
     */
    public DialogBox(String text, Image image) {
        loadFxml("/view/DialogBox.fxml");
        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Flips all children of this {@code DialogBox}.
     */
    public void flip() {
        FXCollections.reverse(getChildren());
        setAlignment(Pos.TOP_LEFT);
    }
}
