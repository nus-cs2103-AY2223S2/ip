package duke.parser;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Processes and handle the deadline command
 */
public class HandleDeadline {
    public HandleDeadline() {
    }

    /**
     * Check whether the input line to add a deadline task is valid and in the correct format or not
     * Perform the adding of deadline task into task list if input is in correct format
     * @param input Entered by user
     * @param tasklist List of existing tasks
     * @param ui Ui that would generate reply for the user
     * @return A String to respond to user through ui, inform user whether task has been added or not
     * @throws WrongFormatException This exception is thrown when input is not in correct format
     */
    public static String performDeadline(String input, TaskList tasklist, Ui ui) throws WrongFormatException {

        try {
            String taskString = input.substring(9, input.indexOf(" /by "));
            String deadline = input.substring(input.indexOf(" /by ") + 5);
            assert input.contains("/by") : "Wrong format for deadline task!";
            LocalDate.parse(deadline);
            Task taskDeadline = new Deadline(taskString, deadline);
            if (tasklist.checkDuplicates(taskDeadline)) {
                return ui.showError("OOPS! You have added this task before already!");
            }
            tasklist.addTask(taskDeadline);
            return ui.showAddTask(taskDeadline.toString(), tasklist.getSize());
        } catch (DateTimeParseException e) {
            return ui.showError("Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10");
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException("deadline <Task description> /by <deadline date>");
        }
    }
}
