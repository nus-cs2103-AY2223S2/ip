package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to execute to find tasks containing specified keyword.
 *
 * @author Cheam Jia Wei
 */
public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList taskList, Ui inter, Storage store) {
        return inter.find(input, taskList);
    }

    public boolean isExit() {
        return false;
    }
}
