package Ava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class DialogBox extends StackPane {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        text.setFont(Font.font ("Verdana", FontWeight.BOLD, 12));
        displayPicture = iv;
        text.setWrapText(true);
        //Setting the maximum width of the label (So that text feild align with text
        text.setMaxWidth(150);

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setStyle("-fx-background-color: lightgrey;-fx-background-radius: 15 15 15 15;-fx-padding: 5px;" +
                "-fx-border-insets: 5px;" +
                "-fx-background-insets: 5px;");

        this.getChildren().addAll(text, displayPicture);
        this.setAlignment(displayPicture, Pos.TOP_RIGHT);
        this.setAlignment(text, Pos.CENTER);
    }

    private void flip() {
        this.setAlignment(displayPicture, Pos.TOP_LEFT);
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
