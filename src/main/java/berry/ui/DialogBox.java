package berry.ui;

import java.io.IOException;
import java.util.Collections;

import berry.MainWindow;
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
        this.style();
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
     * Creates a new dialogue box to show the user dialogue.
     *
     * @param text user's input to berry
     * @param img image of user
     * @return {@code DialogueBox} which represents the user dialogue.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new dialogue box to show berry's dialogue.
     *
     * @param text berry's output to user
     * @param img image of berry
     * @return {@code DialogueBox} which represents berry's dialogue.
     */
    public static DialogBox getBerryDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    private void style() {
        Rectangle r = new Rectangle();
        r.setHeight(100.0);
        r.setArcHeight(50.0);
        r.setArcWidth(50.0);
        r.setWidth(100.0);
        displayPicture.setClip(r);

        this.setSpacing(5);
        this.setFillHeight(false);

        BorderStroke borderStroke = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(10.0), new BorderWidths(1.0));
        Border dialogBorder = new Border(borderStroke);
        dialog.setBorder(dialogBorder);
        dialog.setPadding(new Insets(10, 10, 10, 10));
    }
}
