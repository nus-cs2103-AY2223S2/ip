package kal.gui;

import kal.Kal;
import kal.Ui;
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
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Kal kal;

    private static final String USER_IMAGE_FILEPATH = "/images/You.jpeg";
    private static final String DUKE_IMAGE_FILEPATH = "/images/Kal.jpeg";
    private final Image userImage = new Image(this.getClass().getResourceAsStream(MainWindow.USER_IMAGE_FILEPATH));
    private final Image kalImage = new Image(this.getClass().getResourceAsStream(MainWindow.DUKE_IMAGE_FILEPATH));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getKalDialog(Ui.getWelcome(), kalImage)
        );
    }

    public void setKal(Kal d) {
        kal = d;
    }

    private void catchTermination() {
        if (kal.hasTerminated()) {
            Platform.exit();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Kal's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        this.catchTermination();

        String input = userInput.getText();
        String response = kal.getResponse(input);
        assert !response.isEmpty() : "Response string empty";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKalDialog(response, kalImage)
        );
        userInput.clear();
    }
}
