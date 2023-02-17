package duke.main;
import java.io.IOException;

import duke.exception.DukeException;
import duke.launcher.DialogBox;
import duke.parse.Parser;
import duke.store.Storage;
import duke.task.Task;
import duke.task.TaskList;
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
 *      File name: duke.main.Duke.java
 *      @author: Jerome Neo
 *      Description: The main class for the duke.main.Duke application.
 */
public class Duke extends Application {
    private static final String INSTRUCTIONS = "Didn't catch that, please input valid command. \n \n"
            + "Help me understand by following this format: \n \n \n"
            + "todo {DESCRIPTION} \n \n"
            + "event {DESCRIPTION} /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm \n \n"
            + "deadline {DESCRIPTION} /by dd/mm/yyyy hhmm \n \n"
            + "Do ensure that you date time is of the correct format. \n"
            + "\n"
            + "To query or make changes in the list, use: \n"
            + "list \n"
            + "find {DESCRIPTION} \n"
            + "delete {INDEX} \n"
            + "mark {INDEX} \n"
            + "unmark {INDEX} \n \n"
            + "{DESCRIPTION} indicates the task and {INDEX} indicates the number on the list.";
    private static Parser logic = new Parser();
    private static Storage store = new Storage();
    private static TaskList tasks = new TaskList();
    private static boolean isInitialised = false;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Alien.png"));
    private Image jamie = new Image(this.getClass().getResourceAsStream("/images/Jamie.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    /**
     * Returns a string greeting.
     * @return String
     */
    public String greeting() {
        return "Hi, I am Jamie. What should I put in your task management list?";
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2
        stage.setTitle("Jamie");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        //
        mainLayout.setPrefSize(400.0, 600.0);
        //
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        // You will need to import javafx.scene.Layout region for this
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        //
        userInput.setPrefWidth(325.0);
        //
        sendButton.setPrefWidth(55.0);
        //
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        //
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        //
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);



        // Step 3
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws DukeException, IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren()
                .addAll(
                        DialogBox.getUserDialog(userText, new ImageView(user)),
                        DialogBox.getDukeDialog(dukeText, new ImageView(jamie)));
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException, IOException {
        if (!isInitialised) {
            Storage.readSave(tasks);
            isInitialised = true;
        }
        if (!logic.isValidCommand(input)) {
            return INSTRUCTIONS;
        }

        if (logic.isTaskCommand(input)) {
            Task task = logic.toTask(input);
            tasks.addTask(task);
            return tasks.announceAddedString(task);
        } else if (input.equals("bye")) {
            return "Thank you and goodbye.";
        } else if (input.equals("list")) {
            return tasks.toString();
        } else if (input.startsWith("mark")) {
            int taskNumber = logic.indexToMark(input);
            return tasks.taskMarkedAtIndexString(--taskNumber);
        } else if (input.startsWith("unmark")) {
            int taskNumber = logic.indexToUnmark(input);
            return tasks.taskUnmarkedAtIndexString(--taskNumber);
        } else if (input.startsWith("delete")) {
            int taskNumber = logic.indexToDelete(input);
            return tasks.deleteTaskAtIndexString(--taskNumber);
        } else {
            assert input.startsWith("find") == true : "unhandled command.";
            String description = logic.commandToDescription(input); // find command
            return tasks.matchDescriptionString(description);
        }
    }
}

