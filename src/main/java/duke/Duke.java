package duke;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import ui.UI;
import task.Task;

/**
 * Duke.Duke is a program that help user track list of tasks, it can take in todos, deadline and events tasks and allow
 * users to mark tasks as done or undone and delete the task.
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Duke extends Application {
    final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Storage storage;
    private final UI ui;
    private final Response response;
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Initialises Duke program
     */
    public Duke() {
        this.storage = new Storage("duke.txt");
        this.ui = new UI();
        this.response = new Response();
    }

    public static void main(String[] args) {
    }

    @Override
    public void start(Stage stage) {
        System.out.println(this.ui.showWelcome());
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

//        dialogContainer.getChildren().add(
//                DialogBox.getDukeDialog(this.ui.showWelcome(), dukeImage)
//        );

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
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    public Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * returns the response from user input
     *
     * @param input user input in String form
     * @return resposne in String form
     */
    public String getResponse(String input) {
        int len = input.length();
        int i;
        Parser parser = new Parser();
        String command = parser.parse(input);
        ArrayList<Task> taskList = storage.load();
        String res = "";
        switch (command) {
        case "BYE":
            return response.getByeResponse();
        case "LIST":
            return response.getListResponse(taskList);
        case "DELETE":
            return response.getDeleteResponse(input, taskList, storage);
        case "MARK":
            return response.getMarkResponse(input, taskList, storage);
        case "UNMARK":
            return response.getUnmarkResponse(input, taskList, storage);
        case "TODO":
            return response.getToDoResponse(input, taskList, storage);
        case "DEADLINE":
            return response.getDeadlineResponse(input, taskList, storage);
        case "EVENT":
            return response.getEventResponse(input, taskList, storage);
        case "FIND":
            return response.getFindResponse(input, taskList);
        case "ERROR":
            return response.getErrorResponse();
        default:
            break;
        }
        return "I don't know what that means";
    }
}