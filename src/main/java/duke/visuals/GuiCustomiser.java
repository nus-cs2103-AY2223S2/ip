package duke.visuals;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class GuiCustomiser {
    public static void setMuseAnchorPaneVisuals
            (AnchorPane mainLayout, ScrollPane scrollPane, Button sendButton, TextField userInput) {
        mainLayout.setTopAnchor(scrollPane, 1.0);
        mainLayout.setBottomAnchor(sendButton, 1.0);
        mainLayout.setRightAnchor(sendButton, 1.0);
        mainLayout.setLeftAnchor(userInput, 1.0);
        mainLayout.setBottomAnchor(userInput, 1.0);
    }

    public static void setMuseScrollPaneVisuals(ScrollPane scrollPane) {
        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    public static void setMuseStage(Stage stage) {
        stage.setTitle("Muse");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);
    }

    public static void boxDimensionChange
            (AnchorPane mainLayout, TextField userInput, Button sendButton, VBox dialogContainer) {
        mainLayout.setPrefSize(500.0, 600.0);
        userInput.setPrefWidth(425.0);
        sendButton.setPrefWidth(55.0);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }
}
