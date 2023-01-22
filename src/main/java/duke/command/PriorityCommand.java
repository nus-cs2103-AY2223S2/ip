package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Priority function of Duke.
 */
public class PriorityCommand extends Command {
    public PriorityCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        ui.printList(tasks.sorted());
    };
}
