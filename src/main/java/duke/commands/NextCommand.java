package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a next command.
 */
public class NextCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("What else can I do for you?");
    }
}
