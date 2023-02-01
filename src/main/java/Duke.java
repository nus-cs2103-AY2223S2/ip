import duke.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {
    /**
     * Searches for a task to be marked for completion.
     * @param parsed Parsed command
     * @param tempTaskList List of tasks
     * @return Task that will be marked or unmarked
     */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    TaskList mainTaskList;
    Storage mainStorage;
    Ui mainUi;

    public Duke() {
        mainTaskList = new TaskList();
        mainStorage = new Storage(mainTaskList); // will load from file
        mainUi = new Ui(mainTaskList);
    }

    private static Task getTaskForMarking(String[] parsed, TaskList tempTaskList) {
        int completedIndex = Integer.parseInt(parsed[1]) - 1; // index of the task completed
        Task completedTask = tempTaskList.getTaskAtIndex(completedIndex); // actual task
        return completedTask;
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        try {
            Label userText = new Label(userInput.getText());
            Label dukeText = new Label(getResponse(userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
        }
        catch (Exception ignored) {}
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String command) throws EmptyDescriptionException, IOException {
        String[] toFindFirstWord = Parser.parse(command, Parser.ParseFunctions.SPLIT_ALL); // take a comment

        String first = toFindFirstWord[0];

        ArrayList<String> reply = null;

        switch (first) {
        case "bye":
            System.out.println(first);
            reply = mainUi.printReply("bye");
            break;
        case "mark":
            Task completedTask = getTaskForMarking(toFindFirstWord, mainTaskList);
            completedTask.setCompletion();
            reply = mainUi.printReply("mark", completedTask);

            String[] parsed = Parser.parse(command, Parser.ParseFunctions.TODO);
            mainStorage.changeTaskCompletion(Integer.parseInt(parsed[1]));
            break;
        case "unmark":
            completedTask = getTaskForMarking(toFindFirstWord, mainTaskList);
            completedTask.setCompletion();
            reply = mainUi.printReply("unmark", completedTask);

            parsed = Parser.parse(command, Parser.ParseFunctions.TODO);
            mainStorage.changeTaskCompletion(Integer.parseInt(parsed[1]));
            break;
        case "delete":
            Task toDelete = getTaskForMarking(toFindFirstWord, mainTaskList);

            parsed = Parser.parse(command, Parser.ParseFunctions.TODO);
            mainStorage.deleteTask(Integer.parseInt(parsed[1]));

            reply = mainUi.printReply("delete", toDelete);
            break;
        case "deadline":
            parsed = Parser.parse(command, Parser.ParseFunctions.DEADLINE);
            Task newDeadline = new Deadline(parsed[1], LocalDate.parse(parsed[2]));
            mainStorage.addTask(newDeadline);
            reply = mainUi.printReply("deadline", newDeadline);
            break;
        case "event":
            parsed = Parser.parse(command, Parser.ParseFunctions.EVENT);
            Task newEvent = new Event(parsed[1], LocalDate.parse(parsed[2]), LocalDate.parse(parsed[3]));
            mainStorage.addTask(newEvent);
            reply = mainUi.printReply("event", newEvent);
            break;
        case "find":
            parsed = Parser.parse(command, Parser.ParseFunctions.TODO);

            // ask mainStorage to return an ArrayList of matching tasks
            ArrayList<Task> matchingTasks = mainStorage.getMatchingTasks(parsed[1]);

            // ask mainUi to print out each task one by one
            reply = mainUi.printMatchingTasks(matchingTasks);
            break;
        case "todo":
            try {
                parsed = Parser.parse(command, Parser.ParseFunctions.TODO);
                ToDo newToDo = new ToDo(parsed[1]);
                mainStorage.addTask(newToDo);
                reply = mainUi.printReply("todo", newToDo);
                break;
            } catch (EmptyDescriptionException e) {
                reply.add("  Add an argument");
            }
        default:
            reply = mainUi.printReply(first);
            break;
        }

        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < reply.size(); i++) {
            finalString.append("Duke heard: ").append(reply.get(i)).append("\n");
        }
        return finalString.toString();
    }

}