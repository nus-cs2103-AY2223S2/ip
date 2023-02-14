package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class HandleEvent {
    public HandleEvent() {
    }

    public static String performEvent(String input, TaskList tasklist) throws WrongFormatException {
        boolean correctStartDateFormat = input.contains(" /from ");
        boolean correctEndDateFormat = input.contains(" /to ");
        boolean startDateBeforeEndDate = input.split("/from")[0].length() < input.split("/to")[0].length();
        boolean correctFormat = correctStartDateFormat && correctEndDateFormat && startDateBeforeEndDate;

        if (!correctFormat) {
            throw new WrongFormatException("event 'Task description' /from 'start date' /to 'end date'");
        }

        try {
            LocalDate startDate = LocalDate.parse(
                    input.substring(input.indexOf("/") + 6,
                            input.lastIndexOf("/") - 1));
            LocalDate endDate = LocalDate.parse(
                    input.substring(input.lastIndexOf("/") + 4));
            Task taskEvent = new Event(input, startDate, endDate);
            tasklist.addTask(taskEvent);
            return "Got it. I've added this task: \n  " + taskEvent
                    + "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        } catch (DateTimeParseException e){
            return "Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10";
        }
    }
}
