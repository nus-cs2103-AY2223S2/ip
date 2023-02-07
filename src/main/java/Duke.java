import duke.Deadline;
import duke.EmptyDescriptionException;
import duke.Event;
import duke.NotTaskException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;

import java.io.IOException;
import java.lang.Error;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    static final double WINDOW_MIN_HEIGHT = 600.0;
    static final double WINDOW_MIN_WIDTH = 400.0;
    static final double SCROLL_PANE_HEIGHT = 535.0;
    static final double SCROLL_PANE_WIDTH = 385.0;
    static final double USER_INPUT_WIDTH = 325.0;
    static final double BUTTON_WIDTH = 55.0;
    static final double ANCHOR_OFFSET = 1.0;

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

    /**
     * Starting point for JavaFX where the GUI is set up.
     * @param stage
     */
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
        stage.setMinHeight(WINDOW_MIN_HEIGHT);
        stage.setMinWidth(WINDOW_MIN_WIDTH);

        mainLayout.setPrefSize(WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT);

        scrollPane.setPrefSize(SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(USER_INPUT_WIDTH);

        sendButton.setPrefWidth(BUTTON_WIDTH);

        AnchorPane.setTopAnchor(scrollPane, ANCHOR_OFFSET);

        AnchorPane.setBottomAnchor(sendButton, ANCHOR_OFFSET);
        AnchorPane.setRightAnchor(sendButton, ANCHOR_OFFSET);

        AnchorPane.setLeftAnchor(userInput , ANCHOR_OFFSET);
        AnchorPane.setBottomAnchor(userInput, ANCHOR_OFFSET);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        greetUser(dialogContainer);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });
    }

    /**
     * Greets the user upon startup.
     * @param dialogContainer
     */
    private void greetUser(VBox dialogContainer) {
        Label helloText = new Label("insert ingenious greeting here");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(helloText, new ImageView(duke))
        );
    }

    /**
     * Helper function to handle user input.
     * @param userInput
     * @param dialogContainer
     */
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        try {
            Label userText = new Label(userInput.getText());
            Label dukeText = new Label(getResponse(userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
        } catch (Exception e) {
            throw new Error("this exception should not be here");
        }
        userInput.clear();
    }


    /**
     * Exits the program upon the "bye" command.
     */
    private void closeStage() {
        Platform.exit();
    }

    /**
     * Gets a response given a command which is then sent to the chat box.
     * @param command Command the user takes in
     * @return Output which is printed to the screen
     * @throws NotTaskException
     * @throws IOException
     */
    public String getResponse(String command) throws EmptyDescriptionException, IOException, NotTaskException {
        String[] toFindFirstWord = Parser.parse(command, Parser.ParseFunctions.SPLIT_ALL); // take a comment

        String first = toFindFirstWord[0];

        ArrayList<String> reply = null;

        switch (first) {
        case "bye":
            System.out.println(first);
            reply = mainUi.printReply("bye");
            closeStage();
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
        assert reply.size() > 0;
        StringBuilder finalString = new StringBuilder();
        for (String s : reply) {
            finalString.append(s).append("\n");
        }
        return finalString.toString();
    }

}