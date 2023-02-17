package UI;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
    }

    /**
     * Sets the appearance of the Skylar textbox to be different from that of the user.
     */
    private void setSkylarAppearance() {
        dialog.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        dialog.setStyle("-fx-background-color: burlywood; " +
                "-fx-border-color:  chocolate; " +
                "-fx-border-style: solid inside; " +
                "-fx-border-width: 2; " +
                "-fx-border-image-insets: 5; " +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5");
    }

    public void setErrorAppearance() {
        dialog.setTextFill(Color.RED);
    }
    /**
     * Sets up the user's dialog box.
     * @param text the text to be displayed in the box
     * @param img the user's display icon
     * @return the created dialogbox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Sets up Skylar's dialog box.
     * @param text the text to be displayed in the box
     * @param img the user's display icon
     * @return the created dialogbox
     */
    public static DialogBox getSkylarDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setSkylarAppearance();
        return db;
    }
}