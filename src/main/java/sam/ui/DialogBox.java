package sam.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Represents a dialog with an avatar.
 */
public class DialogBox extends HBox {
    private VBox dialog;
    private Label avatar;

    /**
     * Constructs a new DialogBox.
     *
     * @param t The dialog label.
     * @param a The avatar label.
     */
    public DialogBox(VBox d, Label a) {
        dialog = d;
        avatar = a;

        avatar.setStyle("-fx-font-family: 'monospaced';");
        avatar.setPadding(new Insets(10));

        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.getChildren().addAll(dialog, avatar);
    }

    private void flip() {
        this.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(VBox d, Label a) {
        DialogBox db = new DialogBox(d, a);
        db.flip();
        return db;
    }

    public static DialogBox getSamDialog(VBox d, Label a) {
        return new DialogBox(d, a);
    }
}
