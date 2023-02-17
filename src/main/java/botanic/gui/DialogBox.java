package botanic.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * A class that represents the container for the user's input or for botanic's response,
 * it consists of an ImageView representing the user/botanic icon and a Label containing
 * the user's input or botanic's response.
 * This container is to be displayed onto the screen.
 */
public class DialogBox extends HBox {
    /**
     * The label containing a string representing the user input or botanic's response.
     */
    @FXML
    private Label dialog;
    /**
     * The icon to be displayed, representing the user's icon or the botanic's icon.
     */
    @FXML
    private ImageView displayPicture;

    private static final Color USER_COLOR = Color.LIGHTGOLDENRODYELLOW;
    private static final Color BOTANIC_COLOR = Color.LAVENDERBLUSH;
    private static final CornerRadii DIALOG_BOX_CORNER_RADII = new CornerRadii(10.0);
    private static final Insets INSETS = new Insets(-10);

    private static final BorderWidths BORDER_WIDTH = new BorderWidths(1.0);
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final BorderStrokeStyle BORDER_STROKE_STYLE = BorderStrokeStyle.SOLID;

    /**
     * Instantiates DialogBox.
     *
     * @param text A string that represents user's input or botanic's response.
     * @param img The user's or botanic's icon.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(img);

        this.dialog.setMinHeight(Region.USE_PREF_SIZE);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Adds border to the given dialog box.
     *
     * @param dialogBox The dialog box to which we add borders to.
     */
    private static void designBorder(DialogBox dialogBox) {
        dialogBox.dialog.setBorder(
                new Border(new BorderStroke(
                        DialogBox.BORDER_COLOR, DialogBox.BORDER_STROKE_STYLE,
                        DialogBox.DIALOG_BOX_CORNER_RADII, DialogBox.BORDER_WIDTH,
                        DialogBox.INSETS)));
    }

    /**
     * Sets the background color of the given dialog box to the given color.
     *
     * @param dialogBox The dialog box to which we set the background color.
     * @param color The color to set the background to.
     */
    private static void setBackgroundColor(DialogBox dialogBox, Color color) {
        dialogBox.dialog.setBackground(
                new Background(new BackgroundFill(
                        color, DialogBox.DIALOG_BOX_CORNER_RADII, DialogBox.INSETS)));
    }

    /**
     * Instantiates DialogBox where the ImageView is on the right and text on the left,
     * which represents the user's input.
     *
     * @param text The user's input.
     * @param img The user's icon.
     * @return A DialogBox representing the user's input message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog =  new DialogBox(text, img);
        designBorder(userDialog);
        setBackgroundColor(userDialog, DialogBox.USER_COLOR);
        return userDialog;
    }

    /**
     * Instantiates DialogBox where the ImageView is on the left and text on the right,
     * which represents the botanic's response.
     *
     * @param text Botanic's response.
     * @param img Botanic's icon.
     * @return A DialogBox representing Botanic's response message.
     */
    public static DialogBox getBotanicDialog(String text, Image img) {
        var botanicDialog = new DialogBox(text, img);
        botanicDialog.flip();
        designBorder(botanicDialog);
        setBackgroundColor(botanicDialog, DialogBox.BOTANIC_COLOR);
        return botanicDialog;
    }
}
