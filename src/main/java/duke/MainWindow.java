package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

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

    private Image userImage1 = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage1 = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image userImage = getRoundImage(userImage1, 74);
    private Image dukeImage = getRoundImage(dukeImage1, 74);

    @FXML
    public void initialize() throws IOException {
        duke = new Duke("./data/duke.txt");;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Duke: " + duke.greet(), dukeImage)
        );
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
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }

    private Image getRoundImage(Image image, int radius) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage wImage = new WritableImage(radius * 2, radius * 2);
        PixelWriter pixelWriter = wImage.getPixelWriter();
        PixelReader pixelReader = image.getPixelReader();
        Color c1 = new Color(0, 0, 0, 0);

        int w = (width / 2);
        int h = (height / 2);
        int r = radius * radius;

        for (int i = (width / 2) - radius, k = 0; i < w + radius; i++, k++)
            for (int j = (height / 2) - radius, b = 0; j < h + radius; j++, b++) {
                if ((i - w) * (i - w) + (j - h) * (j - h) > r)
                    pixelWriter.setColor(k, b, c1);
                else
                    pixelWriter.setColor(k, b, pixelReader.getColor(i, j));
            }
        return wImage;
    }
}
