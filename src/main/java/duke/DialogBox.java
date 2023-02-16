package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The DialogBox class handles the UI of the duke bot.
 * It includes methods getUser and getDuke dialog which gives different designs.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a dialogbox with Text and Image.
     * @param l the text to be displayed.
     * @param iv the image to be displayed
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        final Circle clip = new Circle(50, 50, 50);

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        text.setPadding(new Insets(0, 10, 0, 10));
        displayPicture.setClip(clip);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(10);

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

    /**
     * Creates a dialogbox with Text and Image for the User.
     * @param l the text to be displayed.
     * @param iv the image to be displayed
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox user = new DialogBox(l, iv);
        Color color = Color.rgb(136, 172, 224);
        CornerRadii radius = new CornerRadii(20);
        user.setBackground(new Background(new BackgroundFill(color, radius , Insets.EMPTY)));
        return user;
    }
    /**
     * Creates a dialogbox with Text and Image for the bot.
     * @param l the text to be displayed.
     * @param iv the image to be displayed
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        Color color = Color.rgb(173, 216, 230);
        CornerRadii radius = new CornerRadii(20);
        db.setBackground(new Background(new BackgroundFill(color, radius , Insets.EMPTY)));
        return db;
    }
}
