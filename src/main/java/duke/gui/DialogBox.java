package duke.gui;

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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * GUI for dialog box that shows on every user input and response.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
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
        Rectangle clippingMask = new Rectangle(
            displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clippingMask.setArcWidth(100.0);
        clippingMask.setArcHeight(100.0);
        displayPicture.setClip(clippingMask);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user which is shown on the GUI.
     * @param l Literal string of message passed to be shown on the GUI.
     * @param img Image associated with the user shown on GUI.
     * @return Dialog box containing the message and the image.
     */
    public static DialogBox getUserDialog(String l, Image img) {
        return new DialogBox(l, img);
    }

    /**
     * Creates a dialog box for the Duke which is shown on the GUI.
     * @param l Literal string of message passed to be shown on the GUI.
     * @param img Image associated with the Duke shown on GUI.
     * @return Dialog box containing the message and the image.
     */
    public static DialogBox getDukeDialog(String l, Image img) {
        var db = new DialogBox(l, img);
        db.flip();
        return db;
    }
}
