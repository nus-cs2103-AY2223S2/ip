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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * The dialog session class representing either a user's input or the system's
 * response. Displays the text output and the respective user's image, using
 * a FXML layout.
 *
 * @author SeeuSim
 * AY22/23-S2 CS2103T
 */
public class DialogBox extends HBox {
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
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setTranslateX(isFlipped? 15: -15);
        dialog.setTranslateY(20);
        userFrame.setFill(new ImagePattern(img));
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
     * Return the User's input as a DialogBox.
     * @param text The text to display
     * @param img The user's image
     * @return The resultant DialogBox.
     */
    public static DialogBox getMortyDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Return the System's output as a DialogBox.
     * @param text The text to display
     * @param img The user's image
     * @return The resultant DialogBox.
     */
    public static DialogBox getRickDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}