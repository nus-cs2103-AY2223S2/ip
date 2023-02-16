package duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An example of a custom control using FXML. This control represents a dialog box consisting of an ImageView
 * to represent the speaker's face and a label containing text from the speaker.
 */
public class UserDialogBox extends DialogBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private UserDialogBox(String text, Image img) {
        super(text, img, "/view/DialogBox.fxml");
    }

    /**
     * Constructs a User Dialog Box.
     *
     * @param text The dialog of the user.
     * @param img  The image of the user.
     * @return The user dialog box with the text and image of the user.
     */
    public static UserDialogBox getUserDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }

}
