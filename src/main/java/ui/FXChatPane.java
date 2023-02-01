package ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class FXChatPane extends ScrollPane {

    VBox dialogContainer = new VBox();
    Image userImage;
    Image taskMasterImage;

    public FXChatPane(Image userImage, Image taskMasterImage) {
        dialogContainer.heightProperty().addListener((observable) -> {
            this.setVvalue(1.0);
        });
        dialogContainer.setMinHeight(535);
        this.userImage = userImage;
        this.taskMasterImage = taskMasterImage;
        this.setContent(dialogContainer);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVvalue(1);
        this.setFitToWidth(true);
        this.setPrefSize(385, 535);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setStyle("-fx-background-color: #2E4057");
    }

    public static FXChatPane createChatPane(Image userImage, Image taskMasterImage) {
        return new FXChatPane(userImage, taskMasterImage);
    }

    public void addChatToChatPane(String userText, String taskMasterText) {

        FXDialogBox userDialog = FXDialogBox.getUserDialog(userText, userImage);
        dialogContainer.getChildren().add(
                userDialog
        );

        FXDialogBox taskMasterDialog = FXDialogBox.getTaskMasterDialog(taskMasterText, taskMasterImage);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(700),
                event -> dialogContainer.getChildren().add(
                taskMasterDialog)));

        timeline.play();
    }

}
