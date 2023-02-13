package nemo.ui;

import java.io.IOException;

import nemo.Nemo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    @FXML
    private Button sendButton;

    private Nemo nemo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image nemoImage = new Image(this.getClass().getResourceAsStream("/images/Nemo.png"));

    /**
     * Constructs a MainWindow instance.
     *
     * @param nemo Nemo instance.
     */
    public MainWindow(Nemo nemo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.nemo = nemo;
        getWelcomeDialog();
    }


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nemo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nemo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNemoDialog(response, nemoImage)
        );
        userInput.clear();
    }

    private void getWelcomeDialog() {
        dialogContainer.getChildren().addAll(
                DialogBox.getNemoDialog(nemo.getWelcomeMessage(), nemoImage)
        );
    }
}
