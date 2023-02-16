package jeno;

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

    private DialogBox(String text, Image img, String user) {
        if (user.equals("User")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
                fxmlLoader.setController(this);
                fxmlLoader.setRoot(this);
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (user.equals("Jeno")){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/JenoDialogBox.fxml"));
                fxmlLoader.setController(this);
                fxmlLoader.setRoot(this);
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/JenoErrorDialogBox.fxml"));
                fxmlLoader.setController(this);
                fxmlLoader.setRoot(this);
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "User");
    }

    public static DialogBox getJenoDialog(String text, Image img) {
        var db = new DialogBox(text, img, "Jeno");
        db.flip();
        return db;
    }

    public static DialogBox getJenoErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img, "Error");
        db.flip();
        return db;
    }
}