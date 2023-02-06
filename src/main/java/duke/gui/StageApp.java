package duke.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageApp extends Application {

    // Window support values
    private final double MIN_WIDTH = 400.0;
    private final double MIN_HEIGHT = 600.0;

    // JavaFX components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button enterButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        // Scroll container
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        enterButton = new Button("Enter");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, enterButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);

        // Style the nodes
        stage.setTitle("PixlBot");
        stage.setResizable(false);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);

        mainLayout.setPrefSize(MIN_WIDTH, MIN_HEIGHT);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        enterButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(enterButton, 1.0);
        AnchorPane.setRightAnchor(enterButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.show();
    }

}
