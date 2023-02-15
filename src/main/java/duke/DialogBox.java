package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Dialogue box that encapsulates user's input and Duke's reply.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;
    private Circle circle;
    private Image image;
    private ImagePattern imagePattern;

    /**
     * DialogBox constructor
     * @param l Label for input
     * @param iv User or Duke's picture
     */
    public DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.displayPicture = iv;
        this.circle = new Circle(50);
        this.image = this.displayPicture.getImage();
        this.imagePattern = new ImagePattern(this.image);
        this.circle.setFill(this.imagePattern);

        this.text.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.circle);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
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
