package duke;

/**
 * Represents a command-line to-do list interface,
 * capable of adding in todos, events and deadlines.
 */
public class Duke {
    private TaskList tasklist;

    private final Storage storage;
    private final Ui ui;

    public Duke() {
        ui = new Ui();

        storage = new Storage("data/duke.txt");
        try {
            storage.checkFileExists();
            tasklist = storage.loadFile();
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    /**
     * Parses the user's inputs into the command line and runs operations based on input
     */
    public void run() {
        Parser parser = new Parser(tasklist, ui, storage);
        while (ui.hasUserInput()) {
            parser.parse(ui.userInput());
        }
    }


    /**
     * This is the main method that starts the Duke ToDoList command line interface.
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke dukeList = new Duke();
        dukeList.run();
    }
}
