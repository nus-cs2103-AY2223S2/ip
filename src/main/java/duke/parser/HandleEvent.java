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
        /*
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

         */

        try {
            String taskString = input.trim().substring(6, input.indexOf(" /from"));
            String startDate = input.substring(input.indexOf(" /from ") + 7, input.lastIndexOf(" /to "));
            LocalDate.parse(startDate);
            String endDate = input.substring(input.indexOf(" /to ") + 5);
            LocalDate.parse(endDate);
            Task taskEvent = new Event(taskString, startDate, endDate);
            if (tasklist.checkDuplicates(taskEvent)) {
                return ui.showError("OOPS! You have added this task before already!");
            }
            tasklist.addTask(taskEvent);
            return ui.showAddTask(taskEvent.toString(), tasklist.getSize());
        } catch (DateTimeParseException e){
            return ui.showError("Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10");
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException("event 'Task description' /from 'start date' /to 'end date'");
        }
    }
}
