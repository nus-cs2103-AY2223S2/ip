package rick.gui;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import rick.Rick;


/**
 * Represents the main window for the app.
 * This serves as the main UI layout for the GUI.
 *
 * @author SeeuSim
 *         AY22/23-S2 CS2103T
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

    private Rick rick;

    private final Image mortyImage = new Image(this.getClass().getResourceAsStream("/images/Morty.jpeg"));
    private final Image rickImage = new Image(this.getClass().getResourceAsStream("/images/Rick.jpeg"));

    /**
     * Sets the default properties for this window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Populates this app with the Rick server.
     *
     * @param r The Rick server instance.
     */
    public void setRick(Rick r) {
        rick = r;
        dialogContainer.getChildren().add(
                DialogBox.getRickDialog(rick.getWelcome(), rickImage)
        );
    }

    /**
     * Handles the user input and passes it to the system.
     * Displays the system's response and behaves accordingly.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rick.getResponse(input);
        if (response.startsWith("$$MULTI$$")) {
            response = response.replaceFirst("\\$\\$MULTI\\$\\$\n", "");
            String[] responses = response.split("/\\$\\$SEP/\\$\\$");
            ArrayList<DialogBox> dialogs = new ArrayList<>();
            dialogs.add(DialogBox.getMortyDialog(input, mortyImage));

            for (int i = 0; i < responses.length; i++) {
                dialogs.add(DialogBox.getRickDialog(responses[i], rickImage));
            }
            dialogContainer.getChildren().addAll(dialogs);
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getMortyDialog(input, mortyImage),
                    DialogBox.getRickDialog(response, rickImage)
            );
        }

        userInput.clear();

        if (rick.isCloseAppTime()) {
            PauseTransition exitDelay = new PauseTransition(Duration.seconds(1.0));
            exitDelay.setOnFinished(e -> Platform.exit());
            exitDelay.play();
        }
    }
}
