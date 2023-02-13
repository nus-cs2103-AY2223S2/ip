package sam.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog with an avatar.
 */
public class DialogBox extends HBox {
    private Label text;
    private Label avatar;

    /**
     * Constructs a new DialogBox.
     *
     * @param t The dialog label.
     * @param a The avatar label.
     */
    public DialogBox(Label t, Label a) {
        text = t;
        avatar = a;

        text.setWrapText(true);
        avatar.setStyle("-fx-font-family: 'monospaced';");

        avatar.setPadding(new Insets(10));

        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.getChildren().addAll(t, a);
    }

    private void flip() {
        this.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, Label iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    public static DialogBox getSamDialog(Label l, Label iv) {
        return new DialogBox(l, iv);
    }
}
