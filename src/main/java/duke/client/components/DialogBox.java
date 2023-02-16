/**
 * Adapted from SE-EDU JavaFX tutorial https://se-education.org/guides/tutorials/javaFx.html
 */
package duke.client.components;

import java.io.IOException;
import java.net.URL;

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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String RESOURCE_PATH = "/view/DialogBox.fxml";

    @FXML
    private Label dialogLabel;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            URL dialogBoxResource = MainWindow.class.getResource(RESOURCE_PATH);
            FXMLLoader fxmlLoader = new FXMLLoader(dialogBoxResource);
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        dialogLabel.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> childrenList = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(childrenList);
        this.getChildren().setAll(childrenList);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getDialog(String text, Image image, boolean isFlipped) {
        DialogBox dialog = new DialogBox(text, image);
        if (isFlipped) {
            dialog.flip();
        }
        return dialog;
    }
}
