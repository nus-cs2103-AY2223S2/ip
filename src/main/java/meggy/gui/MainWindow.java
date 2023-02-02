package meggy.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import meggy.Meggy;

/**
 * Controller for gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    /** The scroller to move through all dialogs. */
    @FXML
    private ScrollPane scrollPane;
    /** The Vbox that contains all chat history in this session. */
    @FXML
    private VBox dialogContainer;
    /** User's one-line input field. */
    @FXML
    private TextField userInput;
    /** The chatbot currently being used. */
    private Meggy meggy;

    /** WHAT DOES THIS DO? WHEN IS THIS CALLED? */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Updates current chatbot.
     *
     * @param m Non-null. The new chatbot.
     */
    public void setChatbot(Meggy m) {
        meggy = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = meggy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.ofUser(input),
                DialogBox.ofMeggy(response)
        );
        userInput.clear();
    }
}
