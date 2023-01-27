package duke;

/**
 * Main driver class for Duke
 */
public class Duke {

    private Storage storage;
    private Controller controller;
    private UserInterface ui;

    /**
     * Constructor to initialize required components
     */
    public Duke() {
        ui = new UserInterface();
        storage = new Storage();
        controller = new Controller(ui, storage);
    }

    /**
     * Drives Duke program
     */
    public void run() {
        ui.showStartMessage();
        controller.runExecutionLoop();
        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
