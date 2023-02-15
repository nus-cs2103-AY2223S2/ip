package genie.main;
import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setText(text);
        displayPicture.setImage(img);

        //reuse
        Circle circle = new Circle(
                displayPicture.getFitWidth() / 2,
                displayPicture.getFitHeight() / 2,
                displayPicture.getFitWidth() / 2);
        //circle.setStroke(Color.valueOf("0x000000"));
        //circle.setStrokeWidth(10);
        displayPicture.setClip(circle);

        //reuse https://stackoverflow.com/questions/20489908/border-radius-and-shadow-on-imageview
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = displayPicture.snapshot(parameters, null);
        displayPicture.setClip(null);
        displayPicture.setEffect(new DropShadow(20, Color.BLACK));
        displayPicture.setImage(image);
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

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        //reuse https://github.com/nus-cs2103-AY2122S1/ip/commit/431922e7e6dad589d4fef90b2ec80aa3bb4a627e#diff-4bd115e01415644e457f638c000e6faa69119532fcf434e710c78868d688b943
        dialogBox.dialog.getStyleClass().add("user-dialog");
        return dialogBox;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}