package chad.ui;

import java.io.IOException;

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
 * Dialog Box to show the user's conversation with Chad Bot
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private Circle icon;

    /**
     * Constructor class to instantiate new Dialog Box
     * @param message Response to be printed
     * @param img image of the user or chad bot
     */
    public DialogBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(message);
        icon.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box for Chad's response
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Display user dialog on GUI
     * @param text user's message
     * @param img user's image
     * @return dialog box node to display message
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox("You: " + text, img);
    }

    /**
     * Display Chad's response
     * @param text chad's message
     * @param img chad's image
     * @return dialog box to display chad's message
     */
    public static DialogBox getChadDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox("Chad:\n" + text, img);
        dialogBox.flip();
        return dialogBox;
    }
}
