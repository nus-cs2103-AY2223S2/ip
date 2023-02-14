package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.command.Command;
import duke.command.Command.ReturnCode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Provides the layout for the other controls in MainWindow.
 */
public class MainWindow extends AnchorPane {

    /** Relative path to folder containing image assets. */
    private static final String IMG_FOLDER = "/images/";

    /** Name of the image representing user. */
    private static final String USER_IMG = "nigiri-with-shrimp.jpeg";

    /** Name of the image representing duke. */
    private static final String DUKE_IMG = "potato_chips.jpg";

    private final Image userImage = new Image(this.getClass().getResourceAsStream(IMG_FOLDER + USER_IMG));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream(IMG_FOLDER + DUKE_IMG));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        assert duke != null : "No reference to duke";

        ReturnCode code = null;
        String input = userInput.getText();

        try {
            code = Command.parseCommand(input).execute(duke);
        } catch (NullPointerException e) {
            // Failed to parse command, null is returned.
            duke.ui.warn("Sorry, I don't understand your request :(");
            duke.ui.println("Did you spell something wrongly?");
        } catch (DukeException e) {
            duke.ui.warn(e.getMessage());
        } finally {
            String response = duke.ui.getRecentMessages();

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );

            userInput.clear();
        }

        if (code == ReturnCode.EXIT) {
            Platform.exit();
        }
    }
}
