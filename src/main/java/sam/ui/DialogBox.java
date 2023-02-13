package sam.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog with an avatar.
 */
public class DialogBox extends HBox {
    private static final double SIZE = 64.0;

    private VBox dialog;
    private ImageView avatar;

    /**
     * Constructs a new DialogBox.
     *
     * @param t The dialog label.
     * @param a The avatar label.
     */
    private DialogBox(VBox d, ImageView a) {
        dialog = d;
        avatar = a;

        Circle CLIP = new Circle(SIZE / 2, SIZE / 2, SIZE / 2);

        avatar.setFitHeight(SIZE);
        avatar.setFitWidth(SIZE);
        avatar.setClip(CLIP);
        
        HBox.setMargin(avatar, new Insets(0, 0, 0, 16));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(dialog, avatar);
    }

    private void flip() {
        HBox.setMargin(avatar, new Insets(0, 16, 0, 0));
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getLeftDialog(VBox d, ImageView a) {
        DialogBox db = new DialogBox(d, a);
        db.flip();
        return db;
    }

    public static DialogBox getRightDialog(VBox d, ImageView a) {
        return new DialogBox(d, a);
    }
}
