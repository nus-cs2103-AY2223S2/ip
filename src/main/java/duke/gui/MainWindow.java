package duke.gui;

import duke.Duke;
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
        String input = userInput.getText();

        duke.executeCommand(input);
        String response = duke.ui.getRecentMessages();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }
}
