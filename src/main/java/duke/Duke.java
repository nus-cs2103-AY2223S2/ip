package duke;
import duke.command.Command;


/**
 * Duke class with storage,tasks and ui.
 */
public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath
     */
    public Duke(String fileDir, String filePath) {
        this.storage = new Storage(fileDir, filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadContents());
        } catch (Exception e) {
            ui.showResponse(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Launches Duke app.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data", "data/duke.txt").run();
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
                Command command = Parser.parseCommand(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showResponse(e.getMessage());
            }
        }
    }
}
