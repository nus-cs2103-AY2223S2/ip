package duke.ui.gui;

import duke.ui.Ui;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Handles displaying of messages and user interactions via the GUI.
 */
public class Gui implements Ui {
    private static final double STAGE_WIDTH = 600;
    private static final double STAGE_HEIGHT = 600;

    private final Stage stage;

    private MainWindow mainWindow;

    /**
     * Creates a Gui object.
     *
     * @param stage The Stage object provided by JavaFX.
     * @param inputHandler Handles the user's inputs.
     */
    public Gui(Stage stage, BiConsumer<String, Consumer<String>> inputHandler) {
        assert stage != null;

        this.stage = stage;
        mainWindow = new MainWindow(inputHandler);

        setupStage();
        setupStageScene();
        setupStageSize();
    }

    @Override
    public void start() {
        stage.show();
    }

    @Override
    public void print(String message) {
        mainWindow.printDukeMessage(message);
    }

    private void setupStage() {
        stage.setTitle("Duke");
    }

    private void setupStageScene() {
        stage.setScene(new Scene(mainWindow));
    }

    private void setupStageSize() {
        stage.setMinWidth(STAGE_WIDTH);
        stage.setMinHeight(STAGE_HEIGHT);
        stage.setResizable(false);
    }
}
