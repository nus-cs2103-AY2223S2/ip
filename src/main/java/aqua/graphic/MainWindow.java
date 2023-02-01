package aqua.graphic;

import aqua.manager.AppManager;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;


/** The main window of the application. */
public class MainWindow extends UiComponent<VBox> {
    /** String path to FXML file relative to the FXML directory. */
    private static final String PATH_FXML_FILE = "MainWindow.fxml";

    private final AppManager manager;

    @FXML private ScrollPane textScrollPane;
    @FXML private VBox textDisplayArea;
    @FXML private SuggestionTextField inputField;


    /**
     * Constructs a MainWindow from the given LogicManager.
     *
     * @param logicManager - the LogicManager to handle logical processes.
     */
    public MainWindow(LogicManager logicManager) {
        super(PATH_FXML_FILE);

        this.manager = initialiseAppManager(logicManager);

        // add listener to auto scroll to bottom
        textDisplayArea.heightProperty().addListener((ob, o, n) -> {
            textScrollPane.setVvalue(1D);
        });

        // add listener to sync display area size to view port
        textScrollPane.viewportBoundsProperty().addListener((ob, o, n) -> {
            double width = n.getWidth();
            double height = n.getHeight();

            // force width of display area to be view port width
            textDisplayArea.setMinWidth(width);
            textDisplayArea.setPrefWidth(width);
            textDisplayArea.setMaxWidth(width);

            // set the minimum height of the display area to be the view port height
            textDisplayArea.setMinHeight(height);
        });
    }


    /**
     * Initialises an AppManager with the specified LogicManager that has its
     * I/O functions binded to this window.
     *
     * @param logicManager - the LogicManager to handle logical processes.
     * @return the AppManager to run the app.
     */
    private AppManager initialiseAppManager(LogicManager logicManager) {
        IoManager ioManager = new IoManager(this::getInput, this::displayReply);
        return new AppManager(logicManager, ioManager);
    }


    /** Executes the starting processes. */
    public void start() {
        manager.start();
    }


    /**
     * Returns the user's input.
     *
     * <p>{@code inputField} is cleared and the user's input message is
     * displayed in the process.
     *
     * @return the user's input.
     */
    private String getInput() {
        String input = inputField.getText();
        inputField.setText("");
        SpeechBubble bubble = new SpeechBubble(true);
        bubble.setText(input);
        displaySpeechBubble(bubble);
        return input;
    }


    /**
     * Displays the specified reply.
     *
     * @param reply - the reply message to display.
     */
    private void displayReply(String reply) {
        SpeechBubble bubble = new SpeechBubble(false);
        bubble.setText(reply);
        displaySpeechBubble(bubble);
    }


    /**
     * Queues the specified bubble to be displayed in the JavaFx Application
     * Thread.
     *
     * @param bubble - the SpeechBubble to display.
     */
    private void displaySpeechBubble(SpeechBubble bubble) {
        Platform.runLater(() -> textDisplayArea.getChildren().add(bubble.getRoot()));
    }


    /**
     * Handles the ActionEvent when the send button is pressed.
     *
     * <p>The user's input is processed and executed.
     *
     * @param event - the event that occured.
     */
    @FXML
    private void handleSend(ActionEvent event) {
        manager.processInput();
    }


    @FXML
    private void handleKeyRelease(KeyEvent event) {
        if (KeyCode.ENTER.equals(event.getCode())) {
            handleSend(null);
        }
    }
}
