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
import vic.gui.model.DialogBoxInput;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting
 * of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int LINE_LIMIT = 45;
    private static final int LINE_HEIGHT = 30;

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
        DialogBoxInput dialogBoxInput = formatInput(text);
        dialog.setMinHeight(dialogBoxInput.getLineNumber() * LINE_HEIGHT);
        dialog.setText(formatInput(text).getInput());
        displayPicture.setImage(img);
    }



    private static DialogBoxInput formatLine(String input) {
        int lineLimit = LINE_LIMIT;
        String output = input;
        int lines = 0;
        while (lineLimit < output.length()) {
            output = output.substring(0, lineLimit) + "\n   "
                    + output.substring(lineLimit);
            lineLimit += lineLimit + 1;
            lines++;
        }
        return new DialogBoxInput(output, lines);
    }

    private static DialogBoxInput formatInput(String input) {
        StringBuilder result = new StringBuilder();
        String[] lines = input.split("\n");
        int numberOfLines = 0;
        for (String line: lines) {
            DialogBoxInput dialogBoxInput = formatLine(line);
            result.append(dialogBoxInput.getInput());
            result.append("\n");
            numberOfLines += dialogBoxInput.getLineNumber() + 1;
        }
        return new DialogBoxInput(result.toString(), numberOfLines);

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
