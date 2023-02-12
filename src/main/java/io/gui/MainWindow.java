package io.gui;

import command.Command;
import command.Error;
import io.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    Gui gui;

    private final Image userImg = new Image(this.getClass().getResourceAsStream("/images/default_user_pic.png"));
    private final Image dImg = new Image(this.getClass().getResourceAsStream("/images/cyborg_girl.png"));

    /**
     * Setup of tasks and storage.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
        showReply(WELCOME);
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
                DialogueBox.getUserDialogue(userText, userImg));
        userInput.clear();

        Command command = Command.parser().parse(userText).match(
                pr -> pr.first(),
                msg -> Error.of(msg));
        command.execute(gui.taskList, this, gui.storage);
        if (command.isExit()) {
            Platform.exit();
        }
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void showReply(String msg) {
        dialogueContainer.getChildren().addAll(DialogueBox.getBotDialogue(msg, dImg));
    }
}
