package colette.gui;

import colette.Colette;

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
    private static final int exitDelayTimeInSeconds = 5;

    /**
     * Images to be used in GUI.
     * Obtained from https://talesofanswers.tumblr.com/private/78610087338/tumblr_n1y0bhROrr1rsepsd
     */
    private static final Image USER_NEUTRAL_IMAGE = new Image(MainWindow.class
            .getClassLoader().getResourceAsStream("images/Lloyd_Neutral.gif"));
    private static final Image COLETTE_NEUTRAL_IMAGE = new Image(MainWindow.class
            .getClassLoader().getResourceAsStream("images/Colette_Neutral.gif"));
    private static final Image COLETTE_HAPPY_IMAGE = new Image(MainWindow.class
            .getClassLoader().getResourceAsStream("images/Colette_Happy.gif"));
    private static final Image COLETTE_SURPRISED_IMAGE = new Image(MainWindow.class
            .getClassLoader().getResourceAsStream("images/Colette_Surprised.gif"));
    private static final Image COLETTE_SAD_IMAGE = new Image(MainWindow.class
            .getClassLoader().getResourceAsStream("images/Colette_Sad.gif"));
    private static Image currentColetteImage = MainWindow.COLETTE_NEUTRAL_IMAGE;

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

    private Colette colette = new Colette(dirPath);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initialize the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        entry();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.colette.runCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, MainWindow.USER_NEUTRAL_IMAGE),
                DialogBox.getColetteDialog(response, MainWindow.currentColetteImage)
        );
        userInput.clear();
        if (this.colette.isExit()) {
            exit();
        }
    }

    private void entry() {
        String loadFromStorageStatus = this.colette.displayLoadFromStorageStatus();
        dialogContainer.getChildren().addAll(
            DialogBox.getColetteDialog(loadFromStorageStatus, MainWindow.currentColetteImage)
        );
        MainWindow.changeSpriteExpression(SpriteEmotion.NEUTRAL);
        String greetingText = GuiText.showGreeting();
        dialogContainer.getChildren().addAll(
            DialogBox.getColetteDialog(greetingText, MainWindow.currentColetteImage)
        );
    }

    private void exit() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
        HelpWindow.closeHelpWindow();
        PauseTransition delay = new PauseTransition(Duration.seconds(MainWindow.exitDelayTimeInSeconds));
        delay.setOnFinished(event -> this.stage.close());
        delay.play();
    }

    /**
     * Changes the expression of the sprite displayed by
     * Colette's dialogue box.
     *
     * @param emotion The emotion of the sprite to be displayed.
     */
    public static void changeSpriteExpression(SpriteEmotion emotion) {
        switch (emotion) {
        case NEUTRAL:
            MainWindow.currentColetteImage = MainWindow.COLETTE_NEUTRAL_IMAGE;
            break;
        case HAPPY:
            MainWindow.currentColetteImage = MainWindow.COLETTE_HAPPY_IMAGE;
            break;
        case SAD:
            MainWindow.currentColetteImage = MainWindow.COLETTE_SAD_IMAGE;
            break;
        case SURPRISED:
            MainWindow.currentColetteImage = MainWindow.COLETTE_SURPRISED_IMAGE;
            break;
        default:
            break;
        }
    }

}
