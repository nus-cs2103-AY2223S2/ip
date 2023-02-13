import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class for DialogBox object, which displays text to the user.
 *
 * @author Eric Leow Yu Quan
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(boolean isUser, Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        if (!isUser) {
            setDukeStyle(text);
        } else {
            setUserStyle(text);
        }
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    //Code modified from https://www.tabnine.com/code/java/methods/javafx.scene.Node/setStyle
    private void setDukeStyle(Node... nodes) {
        for (Node node : nodes) {
            node.setStyle("-fx-background-color: lightblue");
        }
    }

    private void setUserStyle(Node... nodes) {
        for (Node node : nodes) {
            node.setStyle("-fx-background-color: yellowgreen");
        }
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
        return new DialogBox(true, l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(false, l, iv);
        db.flip();
        return db;
    }
}