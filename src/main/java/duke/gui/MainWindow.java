package duke.gui;

import duke.Duke;

import javafx.animation.PauseTransition;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import javafx.util.Duration;

/**
 * Class that handles the main window of the GUI.
 * Reused from https://se-education.org/guides/tutorials/javaFx.html
 */
public class MainWindow extends AnchorPane {

    private static final String dirPath = "./data/";

    /**
     * Images to be used in GUI.
     * Obtained from https://talesofanswers.tumblr.com/private/78610087338/tumblr_n1y0bhROrr1rsepsd
     */
    private static final Image USER_NEUTRAL_IMAGE = new Image(MainWindow.class
            .getResourceAsStream("../resources/images/Lloyd_Neutral.gif"));
    private static final Image DUKE_NEUTRAL_IMAGE = new Image(MainWindow.class
            .getResourceAsStream("../resources/images/Colette_Neutral.gif"));
    private static final Image DUKE_HAPPY_IMAGE = new Image(MainWindow.class
            .getResourceAsStream("../resources/images/Colette_Happy.gif"));
    private static final Image DUKE_SURPRISED_IMAGE = new Image(MainWindow.class
            .getResourceAsStream("../resources/images/Colette_Surprised.gif"));
    private static final Image DUKE_SAD_IMAGE = new Image(MainWindow.class
            .getResourceAsStream("../resources/images/Colette_Sad.gif"));
    private static Image currentColetteImage = MainWindow.DUKE_NEUTRAL_IMAGE;

    /** JavaFX GUI objects */
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Stage stage;

    private Duke duke = new Duke(dirPath);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        entry();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.duke.runCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, MainWindow.USER_NEUTRAL_IMAGE),
                DialogBox.getDukeDialog(response, MainWindow.currentColetteImage)
        );
        userInput.clear();
        if (this.duke.isExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished( event -> this.stage.close() );
            delay.play();
        }
    }

    private void entry() {
        String loadStatus = this.duke.displayLoadStatus();
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(loadStatus, MainWindow.currentColetteImage)
        );
        MainWindow.changeSpriteExpression(SpriteEmotion.NEUTRAL);
        String greetingText = GuiText.showGreeting();
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(greetingText, MainWindow.currentColetteImage)
        );
    }

    /**
     * Changes the expression of the sprite displayed by
     * Duke's dialogue box.
     *
     * @param emotion The emotion of the sprite to be displayed.
     */
    public static void changeSpriteExpression(SpriteEmotion emotion) {
        switch (emotion) {
        case NEUTRAL:
            MainWindow.currentColetteImage = MainWindow.DUKE_NEUTRAL_IMAGE;
            break;
        case HAPPY:
            MainWindow.currentColetteImage = MainWindow.DUKE_HAPPY_IMAGE;
            break;
        case SAD:
            MainWindow.currentColetteImage = MainWindow.DUKE_SAD_IMAGE;
            break;
        case SURPRISED:
            MainWindow.currentColetteImage = MainWindow.DUKE_SURPRISED_IMAGE;
            break;
        default:
            break;
        }
    }

}
