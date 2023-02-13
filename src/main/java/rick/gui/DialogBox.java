package rick.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Represents a dialog session that represents either a user's input or the
 * system's response. Displays the text output and the respective user's image,
 * using a FXML layout.
 *
 * @author SeeuSim
 *         AY22/23-S2 CS2103T
 */
public class DialogBox extends HBox {
    @FXML
    private VBox textContainer;

    @FXML
    private Label dialog;

    @FXML
    private Circle userFrame;

    /**
     * Constructs the class and indicate to display if it is a User input or
     * the System's response.
     *
     * @param text The text to display
     * @param img The image to display
     * @param isFlipped If it is a user or system output.
     */
    private DialogBox(String text, Image img, boolean isFlipped) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            this.getStylesheets().add("/view/DialogBox.css");
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Set Content
        userFrame.setFill(new ImagePattern(img));
        dialog.setText(text);
        /*
        dialog.setTextAlignment(isFlipped ? TextAlignment.LEFT : TextAlignment.RIGHT);
        if (Arrays.stream(text.split("\n")).max(Comparator.comparingInt(String::length)).get().length() > 50) {
            dialog.setWrappingWidth(280);
        }*/
    }

    /**
     * Flips the dialog box such that the User image is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Generates and returns the User's input as a DialogBox.
     *
     * @param text The text to display
     * @param img The user's image
     * @return The resultant DialogBox.
     */
    public static DialogBox getMortyDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        Font f = FontLoader.getFont(FontLoader.FontStyle.ITALIC);
        assert f != null;
        db.dialog.setFont(f);
        return db;
    }

    /**
     * Generates and returns the System's output as a DialogBox.
     *
     * @param text The text to display
     * @param img The user's image
     * @return The resultant DialogBox.
     */
    public static DialogBox getRickDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        Font displayFont = FontLoader.getFont(FontLoader.FontStyle.ITALIC);
        assert displayFont != null;
        db.dialog.setFont(displayFont);
        db.flip();
        return db;
    }
}
