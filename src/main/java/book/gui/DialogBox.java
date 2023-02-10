package book.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class that encapsulates the {@code DialogBox} used in the {@code Gui} class.
 */
public class DialogBox extends HBox {
    /** {@code ImageView} image of the {@code DialogBox}. */
    @FXML
    private ImageView image;
    /** {@code Label} containing {@code String} text of the {@code DialogBox}. */
    @FXML
    private Label text;

    /**
     * Initializes a {@code DialogBox} instance with the given {@code ImageView} image and
     * {@code Label} containing the {@code String} text, and handles the formatting and layout.
     *
     * @param image {@code ImageView} image used in the {@code DialogBox}.
     * @param text {@code Label} containing text used in the {@code DialogBox}.
     */
    public DialogBox(Image image, String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.text.setText(text);
        this.image.setImage(image);
    }
}
