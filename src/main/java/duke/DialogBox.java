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
        final Circle clip = new Circle(25, 25, 25);

        text.setWrapText(true);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

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
        Color darkBlueColor = Color.rgb(136, 172, 224);
        Color lightBlueColor = Color.rgb(173, 216, 230);
        CornerRadii radius = new CornerRadii(20);
        Background darkBlueBackground = new Background(new BackgroundFill(darkBlueColor, radius , Insets.EMPTY));
        l.setBackground(darkBlueBackground);
        l.setBorder(new Border(new BorderStroke(lightBlueColor, BorderStrokeStyle.SOLID, radius, new BorderWidths(1))));
        DialogBox user = new DialogBox(l, iv);
        //user.setBackground(lightBlueBackground);
        return user;
    }
    /**
     * Creates a dialogbox with Text and Image for the bot.
     * @param l the text to be displayed.
     * @param iv the image to be displayed
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        Color lightBlueColor = Color.rgb(173, 216, 230);
        Color darkBlueColor = Color.rgb(136, 172, 224);
        CornerRadii radius = new CornerRadii(20);
        Background lightBlueBackground = new Background(new BackgroundFill(lightBlueColor, radius , Insets.EMPTY));
        l.setBackground(lightBlueBackground);
        l.setBorder(new Border(new BorderStroke(darkBlueColor, BorderStrokeStyle.SOLID, radius, new BorderWidths(1))));
        var db = new DialogBox(l, iv);
        db.flip();
        //db.setBackground(lightBlueBackground);
        return db;
    }
}
