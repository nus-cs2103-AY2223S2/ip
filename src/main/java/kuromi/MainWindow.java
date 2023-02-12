package kuromi;

import javafx.fxml.FXML;
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

    private Kuromi kuromi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/melody.jfif"));
    private Image kuromiImage = new Image(this.getClass().getResourceAsStream("/images/kuromi.jfif"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKuromi(Kuromi d) {
        kuromi = d;
        String welcome = kuromi.getWelcomeMessage();
        dialogContainer.getChildren().add(KuromiDialogBox.getKuromiDialog(welcome, kuromiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kuromi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                KuromiDialogBox.getKuromiDialog(response, kuromiImage)
        );
        userInput.clear();
    }
}
