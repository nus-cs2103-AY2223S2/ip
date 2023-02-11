package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * A subclass of HBox that represents a dialog box containing an image and speech.
 * @author Nicholas Lee
 */
public class FXDialogBox extends HBox {

    public FXDialogBox(Label label, ImageView imageView) {
        label.setWrapText(true);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(10, 15, 10, 15));
        this.setSpacing(20);
        this.getChildren().addAll(label, imageView);
    }

    /**
     * Reverses the order of the children nodes in the object
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates an instance of FXDialogBox representing a user dialog.
     * @param label The text of the dialog.
     * @param image The profile image of the user.
     * @return A new instance of FXDialogBox with the specified label and a cropped version of the image.
     */
    public static FXDialogBox getUserDialog(String label, Image image) {
        ImageView croppedImage = createCroppedImage(image);
        Label styledLabel = createStyledLabel(label, true);
        return new FXDialogBox(styledLabel, croppedImage);
    }

    /**
     * Creates an instance of FXDialogBox representing a bot dialog.
     * @param label The text of the dialog.
     * @param image The profile image of the bot.
     * @return A new instance of FXDialogBox with the specified label and a cropped version of the image.
     */
    public static FXDialogBox getTaskMasterDialog(String label, Image image) {
        ImageView croppedImage = createCroppedImage(image);
        Label styledLabel = createStyledLabel(label, false);
        var dialogBox = new FXDialogBox(styledLabel, croppedImage);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Creates a cropped version of an image in the shape of a circle.
     * @param image The image to be cropped.
     * @return An ImageView with the specified image cropped in a circular shape.
     */
    private static ImageView createCroppedImage(Image image) {
        Circle circleCrop = new Circle(25, 25, 25);
        ImageView imageView = new ImageView(image);
        imageView.setClip(circleCrop);
        return imageView;
    }

    /**
     * Creates a styled label for dialog text.
     * @param label The text for the label.
     * @param isUser A boolean indicating if the label is for a user dialog.
     * @return A styled label with specified text and color based on whether the role is a user or bot.
     */
    private static Label createStyledLabel(String label, boolean isUser) {
        Label styledLabel = new Label(label);
        String bubbleColor = "#62929E";
        String textColor = "#FFFFFF";
        if (isUser) {
            bubbleColor = "#FCF7FF";
            textColor = "#000000";
        }
        styledLabel.setWrapText(true);

        styledLabel.setStyle(""
                + "-fx-font-size: 13px;"
                + "-fx-padding: 10 20 10 20;"
                + "-fx-background-color: "
                + bubbleColor + ";"
                + "-fx-background-radius: 10;"
                + "-fx-text-fill: " + textColor + " ;");
        return styledLabel;
    }
}
