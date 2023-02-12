package duke;

import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    private Duke duke;

    private Image duckImage = new Image(this.getClass().getResourceAsStream("/images/duck.jpg"));
    private Image dogImage = new Image(this.getClass().getResourceAsStream("/images/dog.png"));

    /** Initializes the GUI */
    @FXML
    public void initialize() {
        duckImage = getRoundedImage(duckImage, 111);
        dogImage = getRoundedImage(dogImage, 270);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "What's up! XyDuke here!\nHow can I be of assistance?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, duckImage));
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
        String response = duke.getResponse(input);
        boolean isExit = false;
        if (response.contains("END_COMMAND")) {
            response = response.substring(11);
            isExit = true;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, dogImage),
                DialogBox.getDukeDialog(response, duckImage));
        userInput.clear();
        if (isExit) {
            Platform.exit();
        }
    }

    //@@author XylusChen-reused
    //Reused from https://stackoverflow.com/questions/68631386/javafx-crop-image-as-a-circle
    private static Image getRoundedImage(Image image, double radius) {
        Circle clip = new Circle(image.getWidth() / 2, image.getHeight() / 2, radius);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
    }
    //@@author
}
