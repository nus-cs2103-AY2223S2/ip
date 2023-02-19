package aqua.graphic;

import aqua.manager.AppManager;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;


/** The main window of the application. */
public class MainWindow extends UiComponent<VBox> {
    private static final String PATH_FXML_FILE = "MainWindow.fxml";

    private static final int MESSAGE_LIMIT = 100;

    private final AppManager manager;

    @FXML private ScrollPane textScrollPane;
    @FXML private VBox textDisplayArea;
    @FXML private TextField inputField;


    /**
     * Constructs a {@code MainWindow} from the given LogicManager.
     *
     * @param logicManager - the LogicManager to handle logical processes.
     */
    public MainWindow(LogicManager logicManager) {
        super(PATH_FXML_FILE);
        this.manager = initialiseAppManager(logicManager);
        initialiseTextDisplayArea();
        initialiseScrollPane();
    }


    private void initialiseTextDisplayArea() {
        // add listener to auto scroll to bottom
        textDisplayArea.heightProperty().addListener((ob, o, n) -> {
            textScrollPane.setVvalue(1D);
        });
    }


    private void initialiseScrollPane() {
        // add listener to sync display area size to view port
        textScrollPane.viewportBoundsProperty().addListener((ob, o, n) -> {
            double width = n.getWidth();
            double height = n.getHeight();

            // force width of display area to be view port width
            textDisplayArea.setMinWidth(width);
            textDisplayArea.setPrefWidth(width);
            textDisplayArea.setMaxWidth(width);

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
        IoManager ioManager = new IoManager(
                this::getInput,
                this::displayReply);
        return new AppManager(logicManager, ioManager);
    }


    /** Executes the starting processes. */
    public void start() {
        manager.start();
    }


    private String getInput() {
        String input = inputField.getText();
        inputField.setText("");
        SpeechBubble bubble = new SpeechBubble(true);
        bubble.setText(input);
        displaySpeechBubble(bubble);
        return input;
    }


    private void displayReply(String reply) {
        SpeechBubble bubble = new SpeechBubble(false);
        bubble.setText(reply);
        Platform.runLater(() -> displaySpeechBubble(bubble));
    }


    private void displaySpeechBubble(SpeechBubble bubble) {
        textDisplayArea.getChildren().add(bubble.getRoot());
        if (textDisplayArea.getChildren().size() > MESSAGE_LIMIT) {
            textDisplayArea.getChildren().remove(0);
        }
    }


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
