package duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An example of a custom control using FXML. This control represents a dialog box consisting of an ImageView
 * to represent the speaker's face and a label containing text from the speaker.
 */
public class DukeDialogBox extends DialogBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DukeDialogBox(String text, Image img) {
        super(text, img, "/view/DukeDialogBox.fxml");
    }

    /**
     * Constructs a Duke Dialog Box.
     *
     * @param text The dialog of Duke.
     * @param img  The image of Duke.
     * @return The Duke dialog box with the text and image of Duke.
     */
    public static DukeDialogBox getDukeDialog(String text, Image img) {
        var db = new DukeDialogBox(text, img);
        return db;
    }
}
