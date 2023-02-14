package duke.gui;

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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tiger.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/owl.jpg"));

    private DialogBox(String text, Profile profile, boolean highlightError) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dialogBoxstyle = "-fx-background-radius: 22;";
        dialog.setText(text);

        if (profile == Profile.USER) {
            displayPicture.setImage(userImage);
        } else {
            displayPicture.setImage(dukeImage);
            dialog.setStyle("-fx-text-fill: white;");
            dialogBoxstyle += "-fx-background-color: #191970;";
            this.flip();
        }

        if (highlightError) {
            dialog.setStyle("-fx-text-fill: red;");
        }
        this.setStyle(dialogBoxstyle);
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

    public static DialogBox getUserDialog(String text, boolean highlightError) {
        return new DialogBox(text, Profile.USER, highlightError);
    }

    public static DialogBox getDukeDialog(String text, boolean highlightError) {
        DialogBox db = new DialogBox(text, Profile.DUKE, highlightError);
        return db;
    }
}
