package duke;

import duke.command.Bye;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * A task management program that reads user input to create and delete tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor to create an instance of Duke.
     *
     * @param filePath The file path to where the list of tasks is stored.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = storage.readData();
        Ui.welcomeMessage();
    }

    /**
     * Starts the Duke program.
     */
    public void run() {
        String input = Ui.getInput();
        Parser parser = new Parser(tasks);
        Command command = null;
        try {
            command = parser.parse(input);
            tasks = command.execute(tasks);
        } catch (DukeException e) {
            Ui.errorMessage(e);
        } finally {
            if(command instanceof Bye) {
                storage.writeData();
                Ui.farewellMessage();
            }
        }
    }

    public TaskList getTasks() {
        return tasks;
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }
}
