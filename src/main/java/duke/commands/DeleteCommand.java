package duke.commands;

import duke.Ui;
import duke.exceptions.IncorrectIndexException;
import duke.exceptions.NeroException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command created when user types delete as first word in input
 */
public class DeleteCommand extends Command {

    public DeleteCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCommand(String userInput) throws NeroException {
        String[] input = userInput.split(" ");
        try {
            int toDelete = Integer.parseInt(input[1]) - 1;
            Task removedTask = taskList.get(toDelete);
            taskList.removeTask(toDelete);
            return ui.printDeletedTasks(removedTask.toString(), taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectIndexException(taskList.getSize());
        }
    }
}
