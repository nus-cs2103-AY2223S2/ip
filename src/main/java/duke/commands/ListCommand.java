package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Command that lists all inside task
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
