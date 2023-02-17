package duke.controller;

import java.io.IOException;

import duke.model.Model;
import duke.util.container.ExecutionResult;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends GridPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private GridPane controlBar;

    @FXML
    private TextField userInput;

    private Model model;
    private Image userImage = new Image(getClass().getResourceAsStream("/images/Reimu.jpg"));
    private Image dukeImage = new Image(getClass().getResourceAsStream("/images/Patchouli.jpg"));

    public MainWindow(Model model) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.prefWidthProperty().bind(widthProperty());
        controlBar.prefWidthProperty().bind(widthProperty());
        this.model = model;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            userInput.clear();
            return;
        }
        ExecutionResult result = model.execute(input);
        if (result.isExit()) {
            Platform.exit();
        }
        DialogBox userBox = new DialogBox(input, userImage);
        DialogBox dukeBox = new DialogBox(result.getMessage(), dukeImage);
        userBox.prefWidthProperty().bind(dialogContainer.widthProperty());
        dukeBox.prefWidthProperty().bind(dialogContainer.widthProperty());
        dukeBox.flip();
        dialogContainer.getChildren().addAll(userBox, dukeBox);
        userInput.clear();
    }
}
