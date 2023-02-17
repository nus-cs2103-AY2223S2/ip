package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to be executed when user wants to search for tasks occurring
 * through the specified date inputted.
 *
 * @author Cheam Jia Wei
 */
public class ThroughCommand extends Command {
    private String input;

    public ThroughCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList taskList, Ui inter, Storage store) {
        return inter.through(input, taskList);
    }

    public boolean isExit() {
        return false;
    }
}
