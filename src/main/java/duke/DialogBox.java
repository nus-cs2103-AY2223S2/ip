package duke;

import static javafx.geometry.Pos.TOP_LEFT;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 * Taken From: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class DialogBox extends HBox {

    private static final int X_PADDING = 30;

    private static final int Y_PADDING = 40;

    private static final int EDGE_RADIUS = 25;

    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;
    @FXML
    private Rectangle textBubble;
    @FXML
    private SVGPath textTriangle;

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
        Text temp = new Text(text);
        int width = (int) temp.getLayoutBounds().getWidth();
        int height = (int) temp.getLayoutBounds().getHeight();

        textBubble.setWidth(width + X_PADDING);
        textBubble.setHeight(height + Y_PADDING);
        textBubble.setFill(Paint.valueOf("#006ee6"));
        textBubble.setX(0);
        textBubble.setY(0);
        textBubble.setArcHeight(EDGE_RADIUS);
        textBubble.setArcWidth(EDGE_RADIUS);
        dialog.setTranslateX(X_PADDING / 2);
        dialog.setTranslateY(X_PADDING / 2);

        textTriangle.setContent("M0 20 L25 20 L0 0 Z");
        textTriangle.setFill(Paint.valueOf("#006ee6"));

        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(TOP_LEFT);

        textTriangle.setContent("M0 20 L25 0 L25 20 Z");

        textBubble.setFill(Paint.valueOf("#4cd964"));
        textTriangle.setFill(Paint.valueOf("#4cd964"));
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
