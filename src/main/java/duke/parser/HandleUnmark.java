package duke.parser;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Processes and handle the unmark command
 */
public class HandleUnmark {
    public HandleUnmark() {
    }

    /**
     * Perform the unmarking of task in task list if input is in correct format and task number is valid
     * @param input Entered by user
     * @param tasklist List of existing tasks
     * @param ui Ui that would generate reply for the user, inform user about status of task
     * @return A String to respond to user through ui, informing user about status of task
     */
    public static String performUnmark(String input, TaskList tasklist, Ui ui) throws DukeException {

        try {
            int taskNum = Integer.parseInt(input.substring(7)) - 1;
            tasklist.unmarkTask(taskNum);
            assert input.trim().split(" ").length > 1 : "There is no task to unmark!";
            return ui.showUnmarkTask(tasklist.getTask(taskNum).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number!");
        }
    }
}
