package duke;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String USER_COLOR = "#15978F";
    private static final String DUKE_COLOR = "#5D1478";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private DialogBox(String text, Image img, String textColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setTextAlignment(TextAlignment.LEFT);
        dialog.setFont(new Font("Helvetica", 12));
        dialog.setTextFill(Color.web(textColor));
        dialog.setPadding(new Insets(5, 5, 5, 5));
        dialog.setStyle("-fx-background-radius: 10 10 10 10;" + "-fx-background-color: #FFD7D7; "
                + "-fx-border-color: #70FF7F;" + "-fx-border-radius: 10 10 10 10;");
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
        return new DialogBox(text, img, USER_COLOR);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, DUKE_COLOR);
        db.flip();
        return db;
    }
}
