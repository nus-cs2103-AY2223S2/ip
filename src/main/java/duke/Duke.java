package duke;

/**
 * Main driver class for Duke
 */
public class Duke {

    private Storage storage;
    private Controller controller;
    private UserInterface ui;

    public Duke() {
        ui = new UserInterface();
        storage = new Storage();
        controller = new Controller(ui, storage);
    }

    public void run() {
        ui.showStartMessage();
        controller.runExecutionLoop();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
