package duke.ui.gui;

import duke.ui.Ui;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Handles displaying of messages and user interactions via the graphical user interface.
 */
public class Gui implements Ui {
    private final Stage stage;

    private final MainWindow mainWindow;

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
        stage.setScene(new Scene(mainWindow));

        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.setResizable(false);
    }

    @Override
    public void start() {
        stage.show();
    }

    @Override
    public void print(String message) {
        mainWindow.printDukeMessage(message);
    }
}
