package duke;

import duke.command.Command;
import duke.exception.DukeException;
import java.util.Scanner;

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
        storage = new Storage(filePath);
        tasks = storage.readData();
        Ui.welcomeMessage();
    }

    /**
     * Starts the Duke program.
     */
    public void run() {
        String input = Ui.getInput();
        Parser parser = new Parser(tasks);
        try {
            Command command = parser.parse(input);
            tasks = command.execute(tasks);
        } catch (DukeException e) {
            Ui.errorMessage(e);
        } finally {
            if(parser.isDone()) {
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
