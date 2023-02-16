package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents Duke's mark function.
 */
public class MarkCommand extends Command {
    /** Constucts the mark command. */
    public MarkCommand() {}

    /**
     * Marks the specific task and stores the resulting takslist.
     *
     * @throws DukeException If user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) throws DukeException {
        Integer taskNum = ui.getTaskNum();
        String s = tasks.mark(taskNum);
        store.saveToFile(tasks);
        return ui.produceDukeOutput("Nice! I've marked this task as done:\n\t" + s + "\n");
    };

}
