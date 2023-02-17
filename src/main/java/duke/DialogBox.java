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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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

    /**
     * Gets dialog box
     * @param text input in dialog box
     * @param img image
     */

    public DialogBox(String text, Image img) {
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
        this.style();
    }


    private void style() {
        Rectangle r = new Rectangle();
        r.setHeight(50.0);
        r.setArcHeight(25.0);
        r.setArcWidth(25.0);
        r.setWidth(50.0);
        displayPicture.setClip(r);

        BorderStroke borderStroke = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(20.0), new BorderWidths(2.0));
        Border dialogBorder = new Border(borderStroke);

        dialog.setStyle("-fx-padding: 15;  -fx-background-radius: 25; "
                + "-fx-background-color: #4682B4; -fx-text-fill: black; -fx-opacity: 70%;");
        dialog.setBorder(dialogBorder);
        dialog.setPadding(new Insets(15, 15, 15, 15));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setStyle("-fx-padding: 15; -fx-background-radius: 25; "
                + "-fx-background-color: #A0522D; -fx-text-fill: black; -fx-opacity: 70%;");


    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

}
