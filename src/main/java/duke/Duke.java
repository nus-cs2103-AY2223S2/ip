package duke;

<<<<<<< HEAD
import duke.commands.Command;

/**
 * The main class of this chatting bot programme.
 */
>>>>>>> branch-A-JavaDoc
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor of this class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.load();
    }

    /**
     * The method that runs the chatting bot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * The main method.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
