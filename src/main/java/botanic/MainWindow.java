package botanic;

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

    private Botanic botanic;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private Image botanicImage = new Image(this.getClass().getResourceAsStream("/images/BotanicIcon.png"));

    /**
     * Binds the v value property to the height of the dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialises Botanic and greets user.
     *
     * @param botanic A Botanic instance.
     */
    public void setBotanic(Botanic botanic) {
        this.botanic = botanic;
        String welcome = "Aloe there! I am BOTanic! How may I help you today?\n";
        this.dialogContainer.getChildren().add(
                DialogBox.getBotanicDialog(welcome, this.botanicImage)
        );
    }

    /**
     * Gets a response from Botanic for the user input given and then
     * gets one DialogBox containing the user input and another containing the response,
     * adds the two DialogBox to the Vbox container.
     * Clear the user input at the end.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = botanic.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotanicDialog(response, botanicImage)
        );
        userInput.clear();
    }
}
