package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Represents a dialog, use code from https://se-education.org/guides/tutorials/javaFx.html
 * with some modification in format setting.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setFont(Font.font("Comic Sans MS"));
        text.setPadding(new Insets(5.0, 10.0, 10.0, 10.0));
        displayPicture.setFitHeight(50.0);
        displayPicture.setFitWidth(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(5.0));
        this.getChildren().addAll(text, displayPicture);
    }

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

