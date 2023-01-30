package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Bye. Hope to see you again soon! :-p");
        storage.save(taskList);
    }
}
