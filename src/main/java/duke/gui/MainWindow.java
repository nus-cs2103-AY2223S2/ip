package duke.gui;

import duke.Duke;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

/**
 * Class that handles the main window of the GUI.
 * Reused from https://se-education.org/guides/tutorials/javaFx.html
 */
public class MainWindow extends AnchorPane {

    private static final String dirPath = "./data/";
    private Duke duke = new Duke(dirPath);

    /** Images to be used in GUI */
    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("../resources/images/DaUser.png"));
    private Image coletteImage = new Image(this.getClass()
            .getResourceAsStream("../resources/images/Colette_Neutral.png"));

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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, coletteImage)
        );
        userInput.clear();
        if (this.duke.isExit()) {
            this.stage.close();
        }
    }

    @FXML
    private void entry() {
        String loadStatus = this.duke.displayLoadStatus();
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(loadStatus, coletteImage)
        );
        String greetingText = GuiText.showGreeting();
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(greetingText, coletteImage)
        );
    }

}
