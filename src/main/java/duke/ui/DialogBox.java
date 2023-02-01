package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Dialog Box to show the user's conversation with Duke Bot
 */
public class DialogBox extends HBox {
    private Label text;
    private Image img;

    /**
     * Constructor class to instantiate new Dialog Box
     * @param text Response to be printed
     * @param img image of the user or duke bot
     */
    public DialogBox(Label text, Image img) {
        this.text = text;
        img = img;

        Circle imgShape = new Circle(50);
        ImagePattern imgPattern = new ImagePattern(img);
        imgShape.setFill(imgPattern);
        this.setPadding(new Insets(20, 0, 20, 0));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, imgShape);
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

    public static DialogBox getUserDialog(Label l, Image img) {
        return new DialogBox(l, img);
    }

    public static DialogBox getDukeDialog(Label l, Image img) {
        var db = new DialogBox(l, img);
        db.flip();
        return db;
    }
}
