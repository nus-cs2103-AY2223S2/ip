package meggy.gui;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import meggy.Meggy;
import meggy.exception.MeggyException;

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

    private ObservableList<Node> chatHistory;

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
        chatHistory = dialogContainer.getChildren();
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
        meggy.bindGui(s -> chatHistory.add(DialogBox.ofMeggy(s)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null;
        assert dialogContainer != null;
        final String input = userInput.getText();
        userInput.clear();
        chatHistory.add(DialogBox.ofUser(input));
        String response;
        try {
            response = meggy.parseAndGetResponse(input);
        } catch (MeggyException e) {
            response = e.getMessage();
        }
        chatHistory.add(DialogBox.ofMeggy(response));
    }
}
