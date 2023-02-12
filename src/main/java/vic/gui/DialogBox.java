package vic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting
 * of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int LINE_LIMIT = 45;

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            URL fxmlLocation = getClass().getResource("/view/DialogBox.fxml");

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(formatInput(text));
        displayPicture.setImage(img);
    }

    private static String formatLine(String input) {
        int lineLimit = LINE_LIMIT;
        String output = input;
        while (lineLimit < output.length()) {
            output = output.substring(0, lineLimit) + "\n   "
                    + output.substring(lineLimit);
            lineLimit += lineLimit + 1;
        }
        return output;
    }

    private static String formatInput(String input) {
        StringBuilder result = new StringBuilder();
        String[] lines = input.split("\n");
        for (String line: lines) {
            result.append(formatLine(line));
            result.append("\n");
        }
        return result.toString();

    }

    /**
     * Flips the dialog box such that the ImageView
     * is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections
                .observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getVicDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
