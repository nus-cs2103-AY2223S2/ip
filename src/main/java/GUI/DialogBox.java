package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

import java.io.Writer;

public class DialogBox extends HBox {

    private Label pictureText;
    private ImageView displayPicture;
    private final Circle circle = new Circle(50, 50, 50);

    public DialogBox(Label l, ImageView iv) {

        displayPicture = iv;
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setStyle("-fx-padding: 20; -fx-border-radius: 20");
        displayPicture.setClip(circle);

        pictureText = new Label(l.getText(), displayPicture);
        pictureText.setWrapText(true);
//        pictureText.setStyle("-fx-background-color: blue");

        pictureText.setContentDisplay(ContentDisplay.RIGHT);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(pictureText);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
//        this.pictureText.setStyle("-fx-background-color: yellow");
        this.setAlignment(Pos.TOP_LEFT);
        this.pictureText.setContentDisplay(ContentDisplay.LEFT);
        this.getChildren().setAll(pictureText);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}