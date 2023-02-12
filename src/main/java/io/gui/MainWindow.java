package io.gui;

import command.Command;
import command.Error;
import io.Storage;
import io.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.TaskList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane implements Ui {
    private static final String WELCOME = "Hello. My name is D.\nI am a simple task manager, designed to serve humanity.";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TaskList taskList;
    private Storage<TaskList> storage;

    private final Image userImg = new Image(this.getClass().getResourceAsStream("/images/default_user_pic.png"));
    private final Image dImg = new Image(this.getClass().getResourceAsStream("/images/cyborg_girl.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
        showReply(WELCOME);
        storage = Storage.of(TaskList.class, "taskList.ser");
        loadTasks();
        showReply(String.format("Current tasks: %s", taskList.toString()));
    }

    /**
     * Obtains user input, creates appropriate command which is executed.
     * User input added to the dialogueContainer as a DialogueBox.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        if (userText.isEmpty()) {
            return;
        }
        dialogueContainer.getChildren().addAll(
                DialogueBox.of(userText, userImg, dialogueContainer));
        userInput.clear();

        Command command = Command.parser().parse(userText).match(
                pr -> pr.first(),
                msg -> Error.of(msg));
        command.execute(taskList, this, storage);
        if (command.isExit()) {
            Platform.exit();
        }
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void showReply(String msg) {
        dialogueContainer.getChildren().addAll(DialogueBox.of(msg, dImg, dialogueContainer));
    }

    private void loadTasks() {
        storage = Storage.of(TaskList.class, "taskList.ser");
        taskList = storage.load().match(
                lst -> lst,
                error -> {
                    switch (error) {
                        case FILE_NOT_FOUND:
                            return new TaskList();
                        case IO_ERROR:
                        case CAST_ERROR:
                            showReply("Error loading tasks, tasks have been reset");
                            return new TaskList();
                        default:
                            return new TaskList();
                    }
                });
    }
}
