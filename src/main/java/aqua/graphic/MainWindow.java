package aqua.graphic;

import aqua.logic.Executor;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainWindow extends UiComponent<VBox> {
    private static final String PATH_FXML_FILE = "MainWindow.fxml";

    private final Executor executor;

    @FXML private ScrollPane textScrollPane;
    @FXML private VBox textDisplayArea;
    @FXML private TextField inputField;


    public MainWindow(LogicManager logicManager) {
        super(PATH_FXML_FILE);

        this.executor = initialiseExecutor(logicManager);

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


    private Executor initialiseExecutor(LogicManager logicManager) {
        IoManager ioManager = new IoManager(this::getInput, this::displayReply);
        return new Executor(logicManager, ioManager);
    }


    public String getInput() {
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
        displaySpeechBubble(bubble);
    }


    private void displaySpeechBubble(SpeechBubble bubble) {
        Platform.runLater(() -> textDisplayArea.getChildren().add(bubble.getRoot()));
    }


    @FXML
    public void handleSend(ActionEvent event) {
        executor.processInput();
    }
}
