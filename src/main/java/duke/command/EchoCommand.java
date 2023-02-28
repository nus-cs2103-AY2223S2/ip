package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command to echo the input of the user
 */
public class EchoCommand extends Command {
    private final String input;

    /**
     * Returns an EchoCommand with the input stored
     *
     * @param input
     */
    public EchoCommand(String input) {
        this.input = input;
    }
    /**
     * Prints the input of the user
     * Display the output via Ui showing the input of the user
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param ui the user interface to interact with the user
     * @param storage used to save the TaskList to be retrieved in the future
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(input);
    }
}
