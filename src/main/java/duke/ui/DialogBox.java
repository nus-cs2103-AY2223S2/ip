package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        BorderStroke borderStroke = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                new CornerRadii(17.0), new BorderWidths(2.0));
        Border dialogBorder = new Border(borderStroke);

        text.setStyle("-fx-padding: 15;  -fx-background-radius: 25; -fx-background-color: #FFFDD0; -fx-text-fill: black; -fx-opacity: 90%;");
        text.setBorder(dialogBorder);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(5, 10, 5, 10));
        this.setSpacing(20);
        this.getChildren().addAll(text, displayPicture);
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