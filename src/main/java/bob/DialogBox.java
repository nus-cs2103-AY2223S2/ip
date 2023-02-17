package bob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * JavaFX component used to display dialogs for Bob
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Returns an instance of DialogBox with the given text and image to display
     * @param l Label containing the text to display
     * @param iv ImageView to display
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setPadding(new Insets(0, 10, 0, 10));
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // Set padding for dialog boxes
        this.setPadding(new Insets(5, 0, 15, 0));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a dialog box representing the user
     * @param l Label containing user input
     * @param iv Image representing the user
     * @return DialogBox
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns a dialog box representing Bob
     * @param l Label containing Bob's response
     * @param iv Image representing Bob
     * @return DialogBox
     */
    public static DialogBox getBobDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.setStyle("-fx-background-color: #89D08E");
        db.flip();
        return db;
    }
}
