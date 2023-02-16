package catbot.ui;

import catbot.CatBot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {
    @FXML
    private HBox inputBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private CatBot catbot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image catbotImage = new Image(this.getClass().getResourceAsStream("/images/DaCatBot.png"));

    /**
     * Initialises the Main Window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        HBox.setHgrow(userInput, Priority.ALWAYS);
    }

    /**
     * Show the welcome message for CatBot to greet the user.
     */
    public void showWelcome() {
        Text logo = new Text(catbot.getLogo());
        logo.setStyle("-fx-text-fill: "
                + "linear-gradient(from 0.0% 100.0% to 100.0% 100.0%, "
                        + "#f5a9b8 0%, #f5a9b8 50%, #5bcefa 50%, #5bcefa 100%);"
                + "-fx-font-family: Monospaced;");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(logo, catbotImage)
        );
    }

    public void setCatBot(CatBot c) {
        catbot = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing CatBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Text input = new Text(userInput.getText());
        Text response = catbot.getResponse(input.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, catbotImage)
        );
        userInput.clear();
    }
}
