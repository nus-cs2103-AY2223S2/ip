package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * An object to show a dialog box
 */
public class DialogBox extends HBox {
    private static double WIDTH = 100.0;
    private static double HEIGHT = 100.0;
    private static double BORDER_WIDTH = 8;
    private static double cornerRadius = 10;

    /*
    More info about color can be found at
    https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html
     */
    private static Color userBoxColor = Color.ALICEBLUE;
    private static Color dukeBoxColor = Color.LIGHTSKYBLUE;
    private static Color errorColor = Color.PINK;

    /**
     * Constructor
     *
     * Takes a label and an image view, wraps the image with border, then renders
     * the label and the image.
     *
     * @param label a label object
     * @param imageView an imageview object, which is the profile pic
     */
    public DialogBox(Label label, ImageView imageView) {
        label.setWrapText(true);
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        BorderPane imageViewWrapper = addBorderToImageView(imageView, BORDER_WIDTH);

        setAlignment(Pos.TOP_RIGHT);
        getChildren().addAll(label, imageViewWrapper);
    }

    /**
     * Adds a border to an image
     *
     * Modified from
     * <a href="https://stackoverflow.com/questions/22614758/issue-with-background-color-in-javafx-8">...</a>
     *
     * @param imageView the image object to render
     * @param boarderWidth the width of the boarder
     * @return an image wrapped with border
     */
    public BorderPane addBorderToImageView(ImageView imageView, double boarderWidth) {
        BorderPane imageViewWrapper = new BorderPane(imageView);
        imageViewWrapper.setPadding(new Insets(boarderWidth));
        return imageViewWrapper;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a dialog object for user
     * @param l user text
     * @param iv an image view object to present text
     * @return a dialog object for user
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox dialogBox = new DialogBox(l, iv);
        dialogBox.setBackground(
                new Background(
                        new BackgroundFill(
                                userBoxColor, new CornerRadii(cornerRadius), Insets.EMPTY)));
        return dialogBox;
    }

    /**
     * Returns a dialog object for duke
     * @param label duke text
     * @param imageView an image view object to present text
     * @param isError whether the input text is the error message, use a different color scheme if so
     * @return a dialog object for duk e
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView, boolean isError) {
        DialogBox dialogBox = new DialogBox(label, imageView);
        dialogBox.flip();
        // https://www.tabnine.com/code/java/methods/javafx.scene.layout.BackgroundFill/%3Cinit%3E
        dialogBox.setBackground(
                new Background(
                        new BackgroundFill(
                                getDukeBoxColor(isError), new CornerRadii(cornerRadius), Insets.EMPTY)));
        return dialogBox;
    }

    public static Color getDukeBoxColor(boolean isError) {
        return isError ? errorColor : dukeBoxColor;
    }
}
