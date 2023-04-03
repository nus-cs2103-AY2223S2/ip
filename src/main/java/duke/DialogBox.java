package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        this.setAlignment(Pos.TOP_RIGHT);
        text.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        text.setPadding(new Insets(10.0f));
        this.setSpacing(20);
        this.setPadding(new Insets(10, 10, 10, 10));
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
        l.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        DialogBox dialogbox = new DialogBox(l, iv);

        return dialogbox;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setBorder(new Border(new BorderStroke(Color.rgb(244, 104, 0),
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
