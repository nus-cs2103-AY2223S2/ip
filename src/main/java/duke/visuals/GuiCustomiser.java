package duke.visuals;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuiCustomiser {
    public static void setMuseAnchorPaneVisuals(
            AnchorPane mainLayout, ScrollPane scrollPane, Button sendButton, TextField userInput) {
        mainLayout.setTopAnchor(scrollPane, 1.0);
        mainLayout.setBottomAnchor(sendButton, 1.0);
        mainLayout.setRightAnchor(sendButton, 1.0);
        mainLayout.setLeftAnchor(userInput, 1.0);
        mainLayout.setBottomAnchor(userInput, 1.0);
    }

    public static void setMuseScrollPaneVisuals(ScrollPane scrollPane) {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    public static void setMuseStage(Stage stage) {
        stage.setTitle("Muse");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    public static void boxDimensionChange(AnchorPane mainLayout, TextField userInput, Button sendButton) {
        mainLayout.setPrefSize(400.0, 600.0);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }
}
