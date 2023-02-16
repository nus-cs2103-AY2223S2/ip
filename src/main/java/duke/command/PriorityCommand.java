package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents the Priority function of Duke.
 */
public class PriorityCommand extends Command {
    /** Constructs the priority command. */
    public PriorityCommand() {}

    /**
     * Informs the user of their tasks in order from earliest date not done.
     *
     */
    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) {
        return ui.produceTaskListOutput(tasks.sorted());
    };
}
