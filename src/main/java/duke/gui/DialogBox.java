// Reference from the given code provided on CS2103 module website
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
import javafx.scene.paint.Paint;


/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String userStyle = "-fx-background-color: #D9A9A9;"
            + "-fx-padding: 10 10 10 10;"
            + "-fx-font-size: 9pt;"
            + "-fx-background-radius: 12;";
    private static final String dukeStyle = "-fx-background-color: #F5DEB3;"
            + "-fx-padding: 10 10 10 10;"
            + "-fx-font-size: 9pt;"
            + "-fx-background-radius: 12;";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String style) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.dialog.setStyle(style);
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        this.dialog.setTextFill(Paint.valueOf("#8B4513"));
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, userStyle);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, dukeStyle);
        db.flip();
        return db;
    }
}
