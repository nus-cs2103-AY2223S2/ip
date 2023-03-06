package aot;

import aot.AddTasks.Task;
import aot.Exceptions.IncompleteInputException;
import aot.Exceptions.InvalidInputException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import aot.munch.Storage;
import aot.munch.TaskList;
import aot.munch.Ui;

import java.util.ArrayList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private TextField welcomeMessage = new TextField(Ui.welcomeMessage());
    @FXML
    private VBox welcomeContainer;
    @FXML
    private Button sendButton;
    @FXML
    private Button exitButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/eren.jpeg"));
    private Image muncher = new Image(this.getClass().getResourceAsStream("/images/levi.jpeg"));
    private Munch munch = new Munch();

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    @FXML
    private void setWelcomeMessage() {
        ArrayList<String> message = new ArrayList<>();
        message.add(welcomeMessage.getText());
        welcomeContainer.getChildren().addAll(DialogBox.getMunchDialog(message, muncher));
    }
    @FXML
    private void setExitButton() {
        Platform.exit();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] words = input.split(" ");
        ArrayList<String> response = TaskList.getResponse(munch.tasks, input, words);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(Ui.erenMessage(input), user),
                DialogBox.getMunchDialog(response, muncher)
        );
        dialogContainer.setPadding(new Insets(10));
        userInput.clear();
        Storage.save(munch.tasks);
    }
}