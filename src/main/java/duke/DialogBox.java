package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        Circle circle = new Circle();
        circle.setRadius(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);

        text.setWrapText(true);
        text.setPadding(new Insets(10));
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setClip(circle);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        l.setStyle(" -fx-font: bold 11pt 'Calibri'; -fx-text-fill: red;");
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setStyle(" -fx-font: 11pt 'Courier New'; -fx-text-fill: black;");
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
