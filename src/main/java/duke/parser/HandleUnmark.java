package duke.parser;

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
    public static String performUnmark(String input, TaskList tasklist, Ui ui) {

        try {
            int taskNum = Integer.parseInt(input.substring(7)) - 1;
            tasklist.unmarkTask(taskNum);
            return ui.showUnmarkTask(tasklist.getTask(taskNum).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError("Please enter a valid task number!");
        }
    }
}
