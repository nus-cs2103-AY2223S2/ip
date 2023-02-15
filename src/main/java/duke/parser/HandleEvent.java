package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class HandleEvent {
    public HandleEvent() {
    }

    public static String performEvent(String input, TaskList tasklist, Ui ui) throws WrongFormatException {
        boolean correctFormat;

        try {
            String[] arrOfString = input.split("/from");
            String[] arrOfStringDate = arrOfString[1].split("/to");
            String trimmedTaskDes = arrOfString[0].trim();
            String trimmedStartDate = arrOfStringDate[0].trim();
            String trimmedEndDate = arrOfStringDate[1].trim();
            correctFormat = trimmedTaskDes.length() > 0 && trimmedStartDate.length() > 0
                    && trimmedEndDate.length() > 0;
            if (!(input.contains (" /from ") && input.contains(" /to "))) {
                correctFormat = false;
            }
        } catch (IndexOutOfBoundsException e){
            correctFormat = false;
        }

        if (!correctFormat) {
            throw new WrongFormatException("event 'Task description' /from 'start date' /to 'end date'");
        }

        try {
            LocalDate startDate = LocalDate.parse(
                    input.substring(input.indexOf("/from") + 6,
                            input.lastIndexOf("/to") - 1));
            LocalDate endDate = LocalDate.parse(
                    input.substring(input.lastIndexOf("/") + 4));
            Task taskEvent = new Event(input, startDate, endDate);
            if (tasklist.checkDuplicates(taskEvent)) {
                return ui.showError("OOPS! You have added this task before already!");
            }
            tasklist.addTask(taskEvent);
            return ui.showAddTask(taskEvent.toString(), tasklist.getSize());
        } catch (DateTimeParseException e){
            return ui.showError("Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10");
        }
    }
}
