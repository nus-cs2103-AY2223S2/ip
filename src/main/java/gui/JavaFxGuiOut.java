package gui;

import static gui.GuiConstants.WINDOW_FXML_PATH;
import static gui.GuiConstants.WINDOW_HEIGHT;
import static gui.GuiConstants.WINDOW_WIDTH;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.Dispatcher;
import types.ISpeaker;
import utilities.DispatcherProvider;


/**
 * The GUI runner.
 */
public class JavaFxGuiOut extends Application implements ISpeaker {
    private final Dispatcher dispatcher = DispatcherProvider.getDefaultDispatcher(() -> {
        Platform.exit();
        System.exit(0);
    }, this);

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /**
     * Responds to prompt string.
     */
    @FXML
    public void handleUserInput() {
        dialogContainer.getChildren().add(DialogueBox.ofUser(userInput.getText()));
        dispatcher.handle(userInput.getText());
        userInput.clear();
    }

    private void initWindow(Stage stage) {
        stage.setTitle("Tach");
        stage.setResizable(true);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setMinWidth(WINDOW_WIDTH);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(WINDOW_FXML_PATH));
        AnchorPane mainLayout = fxmlLoader.load();
        Scene scene = new Scene(mainLayout);

        primaryStage.setScene(scene);
        primaryStage.show();

        initWindow(primaryStage);
    }

    @Override
    public void speak(String s) {
        dialogContainer.getChildren().add(DialogueBox.ofTach(s));
    }
}
