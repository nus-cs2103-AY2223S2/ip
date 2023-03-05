package duke;
import java.io.IOException;
import java.util.Scanner;
import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
/**
 * Duke object which runs the chatbot.
 */
public class Duke extends Application {
    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList taskList = new TaskList();
    private Parser parser = new Parser();
    private Storage storage = new Storage(taskList);
    private Ui ui = new Ui(taskList);

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.

        //Step 1: Get key elements
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2: set window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3: Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //Upon starting GUI, load saved tasks to task list
        taskList = storage.loadTasks();
    }

    @Override
    public void stop() {
        storage.saveTasks();
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /*
    Abstracting out getResponse
    1. make "Command" class with the method "execute"
    - execute takes in parsedCommand
    - Returns the formatted reply
        - abstract each case (SLAP)
        - if not valid, throw the error

    command constructor given tasklist
     */
    private String getResponse(String userInput) {
        String[] parsedCommandString;
        Command command;
        String formattedReply;

        try {
            parsedCommandString = parser.parseCommand(userInput);
            command = new Command(ui, taskList, parsedCommandString);
            formattedReply = command.execute();
        } catch (InvalidCommandException e) {
            formattedReply = e.getMessage();
        }
        return formattedReply;
    }
}
