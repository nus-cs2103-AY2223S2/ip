package duke;

import duke.exceptions.EmptyCommandException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Alice.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Bob.png"));

    private Duke duke;

    private static final Color SIDECOLOR = Color.BURLYWOOD;

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setBackground(new Background(new BackgroundFill(SIDECOLOR, null, null)));
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();


        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(398, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(60.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 5.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);
        AnchorPane.setLeftAnchor(userInput, 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Create Duke instance
        duke = new Duke("tasks.txt");

        //Welcome Msg
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(duke.getWelcomeMsg()), new ImageView(dukeImage))
        );


        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

    }

    private void handleUserInput() {
        Label userText = new Label("\n" + userInput.getText());
        try {
            Label dukeText = new Label(getResponse(userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(userImage)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
            );
        } catch (EmptyCommandException e) {
            return;
        }
        boolean isBye = userInput.getText().equals("bye");
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), event -> closeStageIfBye(isBye)));
        timeline.play();
        userInput.clear();
    }

    private String getResponse(String input) throws EmptyCommandException {
        return duke.process(input);
    }

    private void closeStageIfBye(Boolean isBye) {
        if (isBye) {
            Stage stage = (Stage) scene.getWindow();
            stage.close();
        }
    }

}