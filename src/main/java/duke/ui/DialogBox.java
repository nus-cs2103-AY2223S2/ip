package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * Dialog Box to show the user's conversation with Duke Bot
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private Circle icon;

    /**
     * Constructor class to instantiate new Dialog Box
     * @param message Response to be printed
     * @param img image of the user or duke bot
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
     * Flips the dialog box for Duke's response
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
     * Display Duke's response
     * @param text duke's message
     * @param img duke's image
     * @return dialog box to display duke's message
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dialogBox =  new DialogBox("Duke: " + text, img);
        dialogBox.flip();
        return dialogBox;
    }
}
