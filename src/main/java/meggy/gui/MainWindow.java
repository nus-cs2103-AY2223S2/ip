package meggy.gui;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import meggy.Meggy;
import meggy.Resource;

/**
 * Controller for gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /** The button to send message to the chatbot. */
    @FXML
    private Button sendButton;
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
        assert scrollPane != null;
        assert dialogContainer != null;
        assert sendButton != null;
        assert userInput != null;
        userInput.setFont(GuiUtil.SPLAT_FONT);
        sendButton.setFont(GuiUtil.SPLAT_FONT);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setApDimProperty(ReadOnlyDoubleProperty apHeightProperty, ReadOnlyDoubleProperty apWidthProperty) {
        scrollPane.prefHeightProperty().bind(apHeightProperty.subtract(41));
        scrollPane.prefWidthProperty().bind(apWidthProperty);
        userInput.prefWidthProperty().bind(apWidthProperty.subtract(76));
        dialogContainer.prefWidthProperty().bind(apWidthProperty);
    }

    /**
     * Updates current chatbot.
     *
     * @param m Non-null. The new chatbot.
     */
    public void setChatbot(Meggy m) {
        assert m != null;
        meggy = m;
        dialogContainer.getChildren().add(DialogBox.ofMeggy(Resource.GREETINGS));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null;
        assert dialogContainer != null;
        String input = userInput.getText();
        String response = meggy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.ofUser(input),
                DialogBox.ofMeggy(response)
        );
        userInput.clear();
    }
}
