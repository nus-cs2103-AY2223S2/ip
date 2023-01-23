package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage store) {
        ui.printList(tasks.sorted());
    };
}
