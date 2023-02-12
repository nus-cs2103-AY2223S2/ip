package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(ArrayList<String> tokens) {
        super(tokens);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String res = "Here are the tasks currently in your list: ";
        for (int i = 1; i <= tasks.size(); i++) {
            res += ("\n" + i + ". " + tasks.getTask(i));
        }
        res += ("\nEnd of task list. (currently contains "
                + tasks.size()
                + " tasks)");
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
