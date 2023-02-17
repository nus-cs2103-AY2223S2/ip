package duke.parser;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Processes and handles the delete command
 */
public class HandleDelete {
    public HandleDelete() {
    }

    /**
     * Perform the deletion of a task from task list if input is in correct format and task number is valid
     * @param input Entered by user
     * @param tasklist List of existing tasks
     * @param ui Ui that would generate reply for the user
     * @return A String to respond to user through ui, inform user if the task has been deleted or not
     */
    public static String performDelete(String input, TaskList tasklist, Ui ui) throws DukeException {

        try {
            int taskNum = Integer.parseInt(input.substring(7)) - 1;
            Task taskToRemove = tasklist.getTask(taskNum);
            String removedTaskStr = taskToRemove.toString();
            tasklist.deleteTask(taskNum);
            return ui.showDeleteTask(removedTaskStr, tasklist.getSize());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number!");
        }
    }

}
