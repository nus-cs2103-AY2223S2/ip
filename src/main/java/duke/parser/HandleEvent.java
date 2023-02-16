package duke.parser;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.WrongFormatException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Processes and handle the event command
 */
public class HandleEvent {
    public HandleEvent() {
    }

    /**
     * Check whether the input line to add a deadline task is valid and in the correct format or not
     * Perform the addition of a task from task list if input is in correct format
     * @param input Entered by user
     * @param tasklist List of existing tasks
     * @param ui Ui that would generate reply for the user
     * @return A String to respond to user through ui, inform user whether task has been added or not
     * @throws WrongFormatException This exception is thrown when input is not in correct format
     */
    public static String performEvent(String input, TaskList tasklist, Ui ui) throws WrongFormatException {

        try {
            String taskString = input.trim().substring(6, input.indexOf(" /from"));
            String startDate = input.substring(input.indexOf(" /from ") + 7, input.lastIndexOf(" /to "));
            LocalDate.parse(startDate);
            String endDate = input.substring(input.indexOf(" /to ") + 5);
            LocalDate.parse(endDate);
            assert input.contains("/from") && input.contains("/to"): "Wrong format for event task!";
            Task taskEvent = new Event(taskString, startDate, endDate);
            if (tasklist.checkDuplicates(taskEvent)) {
                return ui.showError("OOPS! You have added this task before already!");
            }
            tasklist.addTask(taskEvent);
            return ui.showAddTask(taskEvent.toString(), tasklist.getSize());
        } catch (DateTimeParseException e) {
            return ui.showError("Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10");
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException("event <Task description> /from <start date> /to <end date>");
        }
    }
}
