package duke.parser;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Processes and handle the mark command
 */
public class HandleMark {
    public HandleMark() {
    }

    /**
     * Perform the marking of task in task list if input is in correct format and task number is valid
     * @param input Entered by user
     * @param tasklist List of existing tasks
     * @param ui Ui that would generate reply for the user
     * @return A String to respond to user through ui, inform user about status of task
     */
    public static String performMark(String input, TaskList tasklist, Ui ui) {

        try {
            int taskNum = Integer.parseInt(input.substring(5)) - 1;
            tasklist.markTask(taskNum);
            assert input.trim().split(" ").length > 1 : "There is no task to mark!";
            return ui.showMarkTask(tasklist.getTask(taskNum).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError("Please enter a valid task number!");
        }
    }
}
