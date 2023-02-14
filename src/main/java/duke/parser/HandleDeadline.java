package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class HandleDeadline {
    public HandleDeadline() {
    }

    public static String performDeadline(String input, TaskList tasklist) throws WrongFormatException {
        boolean correctFormat = input.contains(" /by ") && input.split(" /by ")[0].equals("deadline");

        if (!correctFormat) {
            throw new WrongFormatException("deadline 'Task description' /by 'deadline date'");
        }

        try {
            LocalDate deadline = LocalDate.parse(
                    input.substring(input.indexOf("/") + 4));
            Task taskDeadline = new Deadline(input, deadline);
            tasklist.addTask(taskDeadline);
            return "Got it. I've added this task: \n  " + taskDeadline
                    + "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        } catch (DateTimeParseException e){
            return "Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10";
        }
    }
}
