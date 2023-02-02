package duke.command;

import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Delete command when user specifies "delete" to delete task
 *
 * @author Haiqel Bin Hanaffi
 */
public class DeleteCommand extends Command {
    /**
     * Default constructor
     * @param contents
     */
    public DeleteCommand(String[] contents) {
        super(contents, false);
    }

    /**
     * Saves the task to the memory and displays the result
     * @param taskList List of tasks
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException When saving of task is not possible due to unforseen errors
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NumberFormatException, DukeException {
        int taskIndexDelete = Integer.parseInt(super.parser.convertToUserInput(contents,TypeOfTask.delete,"")) - 1;
        Task taskToBeDeleted = taskList.getTaskByIndex(taskIndexDelete);
        System.out.println("Noted! I've removed this task:");
        System.out.println(String.format("%d. %s",taskIndexDelete + 1,taskToBeDeleted.toString()));
        taskList.removeTask(taskIndexDelete);
        ui.displayResult(TypeOfTask.delete, null, taskList);
    }
}
