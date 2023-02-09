package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(20, 0, 20, 0));
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

    public static DialogBox getUserDialog(Label l, Image i) {
        l.setPadding(new Insets(0, 10, 0, 10));
        l.setTextAlignment(TextAlignment.RIGHT);
        ImageView iv = new ImageView();
        iv.setImage(i);
        DialogBox d = new DialogBox(l, iv);
        return d;
    }

    public static DialogBox getDukeDialog(Label l, Image i) {
        l.setPadding(new Insets(0, 10, 0, 10));
        ImageView iv = new ImageView();
        iv.setImage(i);
        var d = new DialogBox(l, iv);
        d.flip();
        return d;
    }

    public static DialogBox getStartingDialog(Image i) {
        return getDukeDialog(
                new Label("Duke: \n" +
                        "Hello! I am Duke! How may I help you today?"),
                i);
    }
}