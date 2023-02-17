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
    //Solution adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String resourcePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(resourcePath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private static class DialogBoxUser extends DialogBox{
        DialogBoxUser(String text, Image img) {
            super(text, img, "/view/DialogBoxUser.fxml");
        }
    }

    private static class DialogBoxLeo extends DialogBox{
        DialogBoxLeo(String text, Image img) {
            super(text, img, "/view/DialogBoxLeo.fxml");
        }
    }


    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBoxUser(text, img);
    }

    public static DialogBox getLeoDialog(String text, Image img) {
        var db = new DialogBoxLeo(text, img);
        db.flip();
        return db;
    }
}