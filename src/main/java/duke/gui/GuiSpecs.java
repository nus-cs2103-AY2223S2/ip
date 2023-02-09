package duke.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiSpecs {

    public static void initSpecs(ScrollPane scrollPane, AnchorPane mainLayout,
            Stage stage, Scene scene, Button sendButton, TextField userInput, VBox dialogContainer) {

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        initStage(stage, scene);
        initMainLayout(mainLayout);
        initScrollPane(scrollPane, dialogContainer);
        initDialogContainer(dialogContainer, scrollPane);
        initUserInput(userInput);
        initSendButton(sendButton);

    }

    private static void initStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.show();

        // Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private static void initScrollPane(ScrollPane scrollPane, VBox dialogContainer) {
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
    }

    private static void initDialogContainer(VBox dialogContainer, ScrollPane scrollPane) {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private static void initMainLayout(AnchorPane mainLayout) {
        mainLayout.setPrefSize(400.0, 600.0);
    }

    private static void initUserInput(TextField userInput) {
        userInput.setPrefWidth(325.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private static void initSendButton(Button sendButton) {
        sendButton.setPrefWidth(55.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
    }
}
