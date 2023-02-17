package duke.commands;

import duke.Ui;
import duke.exceptions.NeroException;
import duke.task.TaskList;

/**
 * Command created when user types list as first word in input
 */
public class ListCommand extends Command {

    public ListCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCommand(String userInput) throws NeroException {
        return ui.printTasksMessage() + "\n" + taskList.printTasks();
    }

}
