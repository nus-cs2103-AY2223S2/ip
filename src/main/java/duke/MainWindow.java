package duke;

import duke.GUI.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * {@code MainWindow} class that encapsulates the main window of duke application
 */
public class MainWindow extends Application {
    /**
     * handles gui of Duke Application
     */
    protected GUI gui = new GUI();
    protected Duke duke = new Duke(System.getProperty("user.dir"), this.gui);

    /**
     * Default constructor to circumvent NoSuchMethodException problem when running
     * Application.launch
     *
     * Credit to @rmj1405 for providing the tip :)
     */
    public MainWindow() {}

    /**
     * Starts Duke Application with default stage provided
     * @param stage default stage provided to start the application
     */
    @Override
    public void start(Stage stage) {
        gui.startUpProgram(stage);
        gui.runEvent(duke);
    }
}
