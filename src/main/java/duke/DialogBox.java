package duke;

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
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.swing.*;

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
    @FXML
    private HBox hbox;
    @FXML
    private Circle circle;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        hbox.setMaxHeight(Double.POSITIVE_INFINITY);
//        hbox.setMinHeight(Double.NEGATIVE_INFINITY);
//        dialog.setMaxHeight(Double.POSITIVE_INFINITY);
//        dialog.setMinHeight(Double.NEGATIVE_INFINITY);
        dialog.setText(text);
        circle.setFill(new ImagePattern(img));
//        displayPicture.setImage(img);
//        displayPicture.set
//        hbox.autosize();
//        hbox.setMinSize(dialog.USE_PREF_SIZE,dialog.USE_PREF_SIZE);
//        hbox.setMaxSize(dialog.USE_PREF_SIZE,dialog.USE_PREF_SIZE);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-background-color: Beige");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}