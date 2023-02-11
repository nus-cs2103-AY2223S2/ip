package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.parser.Parser;

import duke.tasklist.TaskList;

import duke.ui.Ui;

import duke.ui.components.DialogBox;

import java.util.Scanner;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;


/**
 * Represents the chatbot that helps a user maintain a list of Tasks.
 */
public class Duke extends Application {
    private final TaskList taskList = new TaskList();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private void run() {

        Ui.greet();

        Scanner commandScanner = new Scanner(System.in);
        boolean toExit = false;

        while (true) {

            String userCommand = Ui.getUserCommand(commandScanner);

            try {
                Command command = Parser.parse(userCommand);
                String response = command.execute(this.taskList);
                Ui.show(response);
                toExit = command.isExitCommand();

            } catch (DukeException e) {
                Ui.showError(e);

            } finally {
                Ui.endCommand();

            }

            if (toExit) {
                break;
            }

        }

        commandScanner.close();

    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();

    }

    @Override
    public void start(Stage stage) {
        // Setting up required components

        // The container for the content of the chat to scroll.
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

        // Formatting the window to look as expected
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

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

    /**
     * Returns the chatbot's response to the given user command.
     *
     * @param userCommand The user's text input.
     * @return The String response of the chatbot.
     */
    private String getResponse(String userCommand) {

        String response = "";

        try {
            Command command = Parser.parse(userCommand);
            response = command.execute(this.taskList).strip();

        } catch (DukeException e) {
            response = e.getMessage();

        } finally {
            return response;

        }
    }

}
