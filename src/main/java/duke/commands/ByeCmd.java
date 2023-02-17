package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;

/**
 * Command class for 'bye' keyword.
 * Terminates the program.
 * <p>
 * Command format: "bye"
 */
public class ByeCmd extends Command {
    /**
     * Constructor method.
     *
     * @param taskList Task list to add the Deadline task to
     * @param lineInput Command line input that the user entered
     */
    public ByeCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Exits the program, saves task list to data file, and closes the GUI */
    public String execute() throws CommandExecutionError {
        try {
            Storage.saveToFile(taskList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Goodnight... Zzz...";
    }
}
