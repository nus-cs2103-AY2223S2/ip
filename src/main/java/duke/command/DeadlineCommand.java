package duke.command;

import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Deadline;
import duke.action.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Deadline command when user types "deadline" at the start
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class DeadlineCommand extends Command {
    /**
     * Default constructor
     * @param contents Input from the user
     */
    public DeadlineCommand(String[] contents) {
        super(contents,false);
    }

    /**
     * Saves the task to the memory and displays the result
     * @param taskList List of tasks
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException When saving of task is not possible due to unforseen errors
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        // 1. get the description of the command
        String description = super.parser.convertToUserInput(super.contents, TypeOfTask.deadline, "");
        String[] dateTime = super.parser.convertToUserInput(super.contents, TypeOfTask.deadline, "/by").split(" ");
        Task newTask = new Deadline(description,dateTime[0],dateTime[1]);
        taskList.addTask(newTask);
        ui.displayResult(TypeOfTask.deadline, newTask, taskList);
    }
}
