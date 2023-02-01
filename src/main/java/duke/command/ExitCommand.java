package duke.command;

import duke.storage.TaskList;

/**
 * Exit command when user quits the program. New features to be added.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return "Bye";
    }
}
