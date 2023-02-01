package duke;
import duke.command.Command;

/**
 * Duke class with storage,tasks and ui.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadContents());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Launches Duke app.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs Duke app.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getCommand();
                Command command = Parser.parseCommand(userInput, tasks);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
}