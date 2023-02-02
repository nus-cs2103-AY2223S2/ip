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

    private DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

    }

    public static DialogBox makeUserDialog(String text) {
        DialogBox dialogBox = new DialogBox(new Label("You said: " + text), new ImageView(userImage));
        dialogBox.getChildren().addAll(dialogBox.displayPicture, dialogBox.text);
        dialogBox.setAlignment(Pos.TOP_LEFT);
        return dialogBox;
    }

    public static DialogBox makeDukeDialog(String text) {
        DialogBox dialogBox = new DialogBox(new Label(text), new ImageView(dukeImage));
        dialogBox.getChildren().addAll(dialogBox.text, dialogBox.displayPicture);
        dialogBox.setAlignment(Pos.TOP_RIGHT);
        return dialogBox;
    }

}