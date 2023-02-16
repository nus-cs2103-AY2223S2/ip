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
import util.Pair;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane implements Ui {
    private static final String WELCOME = "Hello. My name is D.\nI am a simple task manager, designed to serve humanity.";
    private final Image userImg = new Image(this.getClass()
            .getResourceAsStream("/images/default_user_pic.png"));
    private final Image dImg = new Image(this.getClass()
            .getResourceAsStream("/images/cyborg_girl.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Gui gui;

    /**
     * Setup of tasks and storage.
     */
    @FXML
    public void initialize() {
        // Check that child nodes were initialized
        assert (scrollPane != null);
        assert (dialogueContainer != null);
        assert (userInput != null);
        assert (sendButton != null);

        dialogueContainer.heightProperty()
                .addListener(observable -> scrollPane.setVvalue(scrollPane.getVmax()));
        showReply(WELCOME);
    }

    /**
     * Obtains user input, creates appropriate command which is executed.
     * User input added to the dialogueContainer as a DialogueBox.
     */
    @FXML
    private void handleUserInput() {
        // Checks that gui properties have been successfully initialized.
        assert (gui != null);
        assert (gui.getTaskList() != null);
        assert (gui.getStorage() != null);

        String userText = userInput.getText();
        if (userText.isEmpty()) {
            return;
        }
        dialogueContainer.getChildren()
                .addAll(DialogueBox.getUserDialogue(userText, userImg));
        userInput.clear();

        Command command = Command.parser()
                .parse(userText)
                .match(Pair::first, Error::of);
        command.execute(gui.getTaskList(), this, gui.getStorage());
        if (command.isExit()) {
            Platform.exit();
        }

    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void showReply(String msg) {
        dialogueContainer.getChildren()
                .addAll(DialogueBox.getBotDialogue(msg, dImg));
    }

    @Override
    public void showError(String errorMsg) {
        dialogueContainer.getChildren()
                .addAll(DialogueBox.getErrorDialogue(errorMsg, dImg));
    }

    /**
     * Sets gui property which allows MainWindow to modify gui properties.
     *
     * @param gui Gui object
     */
    void setGui(Gui gui) {
        this.gui = gui;
    }
}
