package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Controller for DialogBox.
 * Dialog box consists of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    /** Label containing text for dialog */
    @FXML
    private Text dialog = null;

    /** ImageView containing the display picture for the dialog */
    @FXML
    private ImageView displayPicture = null;

    /**
     * Constructs a new DialogBox.
     * Dialog boxes should be constructed using the public factory methods.
     *
     * @param text Text to display inside DialogBox.
     * @param img Display image for the DialogBox.
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
        assert dialog != null : "dialog Label not set up properly in fxml file";
        assert displayPicture != null : "displayPicture ImageView not set up properly in fxml file";
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
     * Creates a new dialog box for the user.
     *
     * @param text Text to display inside DialogBox.
     * @param img Display image for the DialogBox.
     * @return Dialog box for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new dialog box for the duke.
     *
     * @param text Text to display inside DialogBox.
     * @param img Display image for the DialogBox.
     * @return Dialog box for the duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
