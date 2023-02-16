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
import javafx.scene.shape.Circle;


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

        this.dialog.setText(text);
        this.dialog.getStyleClass().add("message");
        this.dialog.setAlignment(Pos.CENTER);
        displayPicture.setImage(img);
        Circle pictureClip = new Circle(
                this.displayPicture.getX() + this.displayPicture.getFitWidth() / 2,
                this.displayPicture.getY() + this.displayPicture.getFitWidth() / 2,
                this.displayPicture.getFitWidth() / 2
        );
        this.displayPicture.setClip(pictureClip);

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

    private Label getDialog() {
        return this.dialog;
    }

    public static MessageBox getUserDialog(String text, Image img) {
        MessageBox userMessage = new MessageBox(text, null);
        userMessage.getDialog().getStyleClass().add("userMessage");
        return userMessage;
    }

    public static MessageBox getDukeDialog(String text, Image img) {
        MessageBox dukeMessage = new MessageBox(text, img);
        dukeMessage.flip();
        dukeMessage.getDialog().getStyleClass().add("dukeMessage");
        return dukeMessage;
    }
}
