package duke;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    //
    // user images
    private static final Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/red.png"));
    private static final Image dukeImage = new Image(DialogBox.class.getResourceAsStream("/images/cyan.png"));

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public static DialogBox makeUserDialog(String text) {
        return new DialogBox(new Label(text), new ImageView(userImage));
    }

    public static DialogBox makeDukeDialog(String text) {
        return new DialogBox(new Label(text), new ImageView(dukeImage));
    }

}