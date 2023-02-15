package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class HandleDeadline {
    public HandleDeadline() {
    }

    public static String performDeadline(String input, TaskList tasklist, Ui ui) throws WrongFormatException{

        try {
            String taskString = input.substring(9, input.indexOf(" /by "));
            String deadline =  input.substring(input.indexOf(" /by ") + 5);
            LocalDate.parse(deadline);
            Task taskDeadline = new Deadline(taskString, deadline);
            if (tasklist.checkDuplicates(taskDeadline)) {
                return ui.showError("OOPS! You have added this task before already!");
            }
            tasklist.addTask(taskDeadline);
            return ui.showAddTask(taskDeadline.toString(), tasklist.getSize());
        } catch (DateTimeParseException e){
            return ui.showError("Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10");
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException("deadline 'Task description' /by 'deadline date'");
        }
    }
}
