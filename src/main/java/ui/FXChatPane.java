package ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * A subclass of ScrollPane that represents a chat interface between the user and the bot.
 * @author Nicholas Lee
 */
public class FXChatPane extends ScrollPane {

    private final VBox dialogContainer = new VBox();
    private final Image userImage;
    private final Image taskMasterImage;

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

    /**
     * Creates an instance of FXChatPane.
     * @param userImage The profile image of the user.
     * @param taskMasterImage The profile image of the task master.
     * @return A new instance of FXChatPane.
     */
    public static FXChatPane createChatPane(Image userImage, Image taskMasterImage) {
        return new FXChatPane(userImage, taskMasterImage);
    }

    /**
     * Adds a user dialog and bot dialog to the dialogContainer VBox.
     * The bot dialog is added with a short delay after the user dialog.
     * @param userText Text containing what the user says.
     * @param taskMasterText Text containing what the bot says.
     */
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
