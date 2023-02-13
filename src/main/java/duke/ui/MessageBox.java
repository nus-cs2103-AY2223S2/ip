package duke.ui;
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
 * FXML messagebox object that represents the text message sent into the UI.
 */
public class MessageBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    MessageBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
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

    public static MessageBox getUserDialog(String text, Image img) {
        return new MessageBox(text, img);
    }

    public static MessageBox getDukeDialog(String text, Image img) {
        var dukeMessage = new MessageBox(text, img);
        dukeMessage.flip();
        return dukeMessage;
    }
}
