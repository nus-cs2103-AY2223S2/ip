package alfred.components;


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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a Dialog Box object to represent the messages from Alfred or the User.
     * @param text The text that is either from the user or Alfred.
     * @param img The image representing Alfred or the user.
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);


        displayPicture.setImage(img);
        double height = displayPicture.getFitHeight();
        double width = displayPicture.getFitWidth();
        Circle circle = new Circle(width / 2, height / 2, Math.min(height, width) / 2);
        displayPicture.setClip(circle);
        displayPicture.setFitWidth(circle.getRadius() * 2);
        displayPicture.setFitHeight(circle.getRadius() * 2);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Creates a dialog box that contains the user image and text.
     * @param text The text that the user typed into the GUI.
     * @param img The image that represents the user.
     * @return The dialog box that contains the text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box that contains Alfred image and response.
     * @param text Alfred's response to the user text.
     * @param img Alfred's image.
     * @return The dialog box that contains the text and the response.
     */
    public static DialogBox getAlfredDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
