package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @see Task
 * @since 11
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Khabib.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Ronaldo.png"));

    List<Timeline> recurResponse = new ArrayList<>();

    Image image = new Image(this.getClass().getResourceAsStream("/images/Wallpaper.png"));
    BackgroundSize backgroundSize = new BackgroundSize(1, 1, false, false, false, false);
    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background = new Background(backgroundImage);

    /**
     * Initialize is ran at the start of the javafx lifecycle.
     * The greetings message is done at initialize so that once the user launches the chatbot, it will always be shown.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ByteArrayOutputStream storeString = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(storeString);
        PrintStream oldPrintStream = System.out;
        System.setOut(printStream);
        Ui ui = new Ui("Greetings");
        ui.showWelcome();
        System.out.flush();
        System.setOut(oldPrintStream);
        String greeting = storeString.toString();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greeting, dukeImage));
        duke = new Duke();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = duke.getResponse(input, recurResponse);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        new TaskScheduler(recurResponse, dialogContainer, dukeImage, input);

        dialogContainer.setBackground(background);

        userInput.clear();
    }
}
