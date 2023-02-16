package duke.visuals;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Creates the default dialog box that we will see in Muse's interface.
     * This includes text (label l) and an image (imageview iv).
     *
     * @param l this is a label, and provides the text that will be displayed in an
     *                          individual DialogBox.
     * @param iv This provides the image (aka the profile pic) that will be viewed
     *                          in each individual DialogBox.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        text.setPadding(new Insets(0, 4, 0, 4));
        text.setMaxWidth(400);
        text.setWrapText(true);
        displayPicture.setFitHeight(65.0);
        displayPicture.setFitWidth(65.0);

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
     * This is a helper function that generates a DialogBox, with the user's avatar image.
     *
     * @param l this provides text for the user's DialogBox.
     * @param iv this provides the image for user's DialogBox.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * This is a helper function that generates a DialogBox, with muse's (the bot) avatar image.
     *
     * @param l this provides text for the muse's DialogBox.
     * @param iv this provides the image for muse's DialogBox.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
