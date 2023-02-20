package botanic.gui;

//@@author HmuuMyatMoe-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
import botanic.Botanic;
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
    private Botanic botanic;

    //UserIcon.png is taken from https://www.flaticon.com/free-icon/sun_4478387?related_id=4478387&origin=pack
    //Attribution for UserIcon.png:
    // <a href="https://www.flaticon.com/free-icons/cloudy" title="cloudy icons">
    // Cloudy icons created by Freepik - Flaticon</a>
    private Image userImage = new Image(getClass()
            .getResourceAsStream("/images/UserIcon.png"));
    //BotanicIcon.png is taken from https://www.flaticon.com/free-icon/apple_4478115
    //Attribution for BotanicIcon.png:
    // <a href="https://www.flaticon.com/free-icons/apple" title="apple icons">
    // Apple icons created by Freepik - Flaticon</a>
    private Image botanicImage = new Image(getClass()
            .getResourceAsStream("/images/BotanicIcon.png"));

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
        String welcome = this.botanic.getWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getBotanicDialog(welcome, botanicImage)
        );
    }

    /**
     * Gets a response from Botanic for the user input given.
     * Then, gets one DialogBox containing the user input and another containing the response,
     * and add both to the Vbox container.
     * Clear the user input at the end.
     */
    @FXML
    private void handleUserInput() {
        //get user input and get response from botanic
        String input = userInput.getText();
        String response = botanic.getResponse(input);

        //get dialog boxes containing user input and botanic response
        //then add the dialog boxes to vbox container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotanicDialog(response, botanicImage)
        );
        userInput.clear();
    }
}
//@@author
