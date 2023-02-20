package voile.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import voile.model.Model;
import voile.util.container.ExecutionResult;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends GridPane implements FxmlComponent {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private GridPane controlBar;

    @FXML
    private TextField userInput;

    private Model model;
    private Image reimuImage = new Image(getClass().getResourceAsStream("/images/Reimu.jpg"));
    private Image patchouliImage =
            new Image(getClass().getResourceAsStream("/images/Patchouli.jpg"));

    /**
     * Creates a new {@code MainWindow} with the given model.
     *
     * @param model the model to generate responses from inputs
     */
    public MainWindow(Model model) {
        loadFxml("/view/MainWindow.fxml");
        this.model = model;
    }

    /**
     * Initializes the {@code MainWindow}.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.prefWidthProperty().bind(widthProperty());
        controlBar.prefWidthProperty().bind(widthProperty());
        DialogBox patchouliBox = new DialogBox(Messages.GREETING_MESSAGE, patchouliImage);
        patchouliBox.prefWidthProperty().bind(dialogContainer.widthProperty());
        patchouliBox.flip();
        dialogContainer.getChildren().add(patchouliBox);
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
        DialogBox reimuBox = new DialogBox(input, reimuImage);
        DialogBox patchouliBox = new DialogBox(result.getMessage(), patchouliImage);
        reimuBox.prefWidthProperty().bind(dialogContainer.widthProperty());
        patchouliBox.prefWidthProperty().bind(dialogContainer.widthProperty());
        patchouliBox.flip();
        dialogContainer.getChildren().addAll(reimuBox, patchouliBox);
        userInput.clear();
    }
}
