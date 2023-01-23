
import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke class represents a CLI chatbot that performs operations based on CLI user input.
 * <p>
 * Currently, Duke accepts the commands: {@code echo, list, mark, unmark, todo, deadline, event, bye}
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage store;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = Ui.getInstance();
        try {
            store = new Storage("src/main/resources/duke.txt");
            tasks = store.loadFromFile();
            tasks = tasks == null ? new TaskList() : tasks;
        } catch (Exception e) {
            ui.printException(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Main function of Duke.
     *
     * @param args There are no options available for now.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs Duke as a CLI chatbot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.getCommand();
                Command c = Parser.parseCommand(command);
                c.execute(tasks, ui, store);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }
    }

}
