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

    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static FXDialogBox getUserDialog(String label, Image image) {
        ImageView croppedImage = createCroppedImage(image);
        Label styledLabel = createStyledLabel(label, true);
        return new FXDialogBox(styledLabel, croppedImage);
    }

    public static FXDialogBox getTaskMasterDialog(String label, Image image) {
        ImageView croppedImage = createCroppedImage(image);
        Label styledLabel = createStyledLabel(label, false);
        var dialogBox = new FXDialogBox(styledLabel, croppedImage);
        dialogBox.flip();
        return dialogBox;
    }

    private static ImageView createCroppedImage(Image image) {
        Circle circleCrop = new Circle(25, 25, 25);
        ImageView imageView = new ImageView(image);
        imageView.setClip(circleCrop);
        return imageView;
    }

    private static Label createStyledLabel(String label, boolean isUser) {
        Label styledLabel = new Label(label);
        String bubbleColor = "#62929E";
        String textColor = "#FFFFFF";
        if (isUser) {
            bubbleColor = "#FCF7FF";
            textColor = "#000000";
        }
        styledLabel.setWrapText(true);

        styledLabel.setStyle("" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 10 20 10 20;" +
                "-fx-background-color: " + bubbleColor + ";" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: " + textColor + " ;");
        return styledLabel;
    }
}
