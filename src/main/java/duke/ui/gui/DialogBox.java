package duke.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

/**
 * Controller for DialogBox.
 * <p>
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label containing the text
 * from the speaker.
 * </p>
 */
public class DialogBox extends HBox {
    private static final String FXML_PATH = "/view/DialogBox.fxml";

    private static final double CLIP_CIRCLE_RADIUS = 25;

    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String message, Image img) {
        loadFxml();
        setNodeValues(message, img);
    }

    /**
     * Creates and returns a dialog box with a message that appears to be spoken by the user.
     *
     * @param message The message.
     * @param img The image of the user.
     * @return A dialog box with a message that appears to be spoken by the user.
     */
    public static DialogBox getUserDialog(String message, Image img) {
        return new DialogBox(message, img);
    }

    /**
     * Creates and returns a dialog box with a message that appears to be spoken by Duke.
     *
     * @param message The message.
     * @param img The image of Duke.
     * @return A dialog box with a message that appears to be spoken by Duke.
     */
    public static DialogBox getDukeDialog(String message, Image img) {
        DialogBox dialogBox = new DialogBox(message, img);
        dialogBox.flip();

        return dialogBox;
    }

    private void loadFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_PATH));

            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);

            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNodeValues(String message, Image img) {
        dialog.setText(message);
        displayPicture.setImage(img);
        displayPicture.setClip(getDisplayPictureClip());
    }

    private Node getDisplayPictureClip() {
        Circle clip = new Circle();
        clip.setRadius(CLIP_CIRCLE_RADIUS);
        clip.setCenterX(CLIP_CIRCLE_RADIUS);
        clip.setCenterY(CLIP_CIRCLE_RADIUS);

        return (Node) clip;
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}
