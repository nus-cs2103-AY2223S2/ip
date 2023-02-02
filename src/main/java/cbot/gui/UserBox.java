package cbot.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
public class UserBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private UserBox(String text, Image img, String viewPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(viewPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setPadding(new Insets(10.0));

        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50.0, 50.0, 50.0));
    }

    /**
     * Constructs a UserBox representing the user's input.
     *
     * @param text The user's input.
     * @param img The user's Image.
     * @return A new UserBox, for the user.
     */
    public static UserBox getUserDialog(String text, Image img) {
        return new UserBox(text, img, "/view/UserBox.fxml");
    }
}
