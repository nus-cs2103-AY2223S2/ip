package sam.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog with an avatar.
 */
public class DialogBox extends HBox {

    private static final String FXML = "/view/DialogBox.fxml";
    private static final double SIZE = 64.0;

    @FXML
    private VBox dialogContainer;

    @FXML
    private ImageView avatar;

    /**
     * Constructs a new DialogBox.
     *
     * @param t The dialog label.
     * @param a The avatar label.
     */
    private DialogBox(Image a, Node... nodes) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogContainer.getChildren().addAll(nodes);

        Circle clip = new Circle(SIZE / 2, SIZE / 2, SIZE / 2);
        avatar.setImage(a);
        avatar.setFitHeight(SIZE);
        avatar.setFitWidth(SIZE);
        avatar.setClip(clip);

        HBox.setMargin(avatar, new Insets(0, 0, 0, 16));
        this.setAlignment(Pos.TOP_RIGHT);
    }

    private void flip() {
        HBox.setMargin(avatar, new Insets(0, 16, 0, 0));
        this.setAlignment(Pos.TOP_LEFT);

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getLeftDialog(Image a, Node... nodes) {
        DialogBox db = new DialogBox(a, nodes);
        db.flip();
        return db;
    }

    public static DialogBox getRightDialog(Image a, Node... nodes) {
        return new DialogBox(a, nodes);
    }
}
