package duke.fxui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Dialog box consisting of the response of the chatbot or the query/command by the user. It shows the profile image
 * icon on whom the message is from and the message content itself. The chatbot dialog box would be aligned to the
 * left side, while the one by the user is aligned to the right side.
 */
public class DialogBox extends HBox {
    /**
     * The container for text displayed in the dialog box.
     */
    @FXML
    private Label dialog;
    /**
     * Icon image of the user/chatbot.
     */
    @FXML
    private ImageView displayImage;

    /**
     * Constructor for a dialog box with the provided text, with the corresponding profile image. It is right aligned.
     *
     * @param text  The text to be displayed
     * @param image The profile image
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayImage.setImage(image);
        setAlignment(Pos.TOP_RIGHT);

        // Expands label to fit the text inside label
        dialog.setMinWidth(Region.USE_PREF_SIZE);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
    }

    /**
     * Factory constructor to create a dialog box corresponding to the user. Specifically, the dialog box is right
     * aligned with the respective text and profile image of the user.
     *
     * @param text  The text to be displayed
     * @param image The profile image
     * @return A user dialog box
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Factory constructor to create a dialog box corresponding to the chatbot. Specifically, the dialog box is left
     * aligned with the respective text and profile image of the chatbot.
     *
     * @param text  The text to be displayed
     * @param image The profile image
     * @return A chatbot dialog box
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Flips the dialog box to be left aligned.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}
