package dukes.engine;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    /** Handles text component of input */
    @FXML
    private Label dialog;
    @FXML
    private Label username;

    /** Handles the icon component of input */
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setFont(new Font("Courier New", 13));
        username.setText(user);
        username.setFont(new Font("Courier New", 10));

        dialog.setWrapText(true);
        dialog.setMnemonicParsing(false);
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

    /**
     * Create User's dialog box
     *
     * @param text user input text
     * @param img user icon
     * @return a DialogBox representing user's activity
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox output = new DialogBox(text, img, "heeeyi");
        output.setBackground(new Background(
                new BackgroundFill(Color.web("#DDFBC9"), CornerRadii.EMPTY, Insets.EMPTY)
        ));
        return output;
    }


    /**
     * Create chat engine's dialog box
     *
     * @param text chat engine output text
     * @param img chat engine icon
     * @return a DialogBox representing chat engine's activity
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "duke");
        db.flip();
        db.setBackground(new Background(
                new BackgroundFill(Color.web("#C9FBEC"), CornerRadii.EMPTY, Insets.EMPTY)
        ));
        return db;
    }
}