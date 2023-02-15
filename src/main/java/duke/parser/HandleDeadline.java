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

    public static String performDeadline(String input, TaskList tasklist) throws WrongFormatException{
        boolean correctFormat = true;
        try {
            String taskCommand = input.substring(9);
            String[] arrOfString = taskCommand.split("/by");
            String trimmedTaskDes = arrOfString[0].trim();
            String trimmedDeadline = arrOfString[1].trim();
            correctFormat = trimmedTaskDes.length() > 0 && trimmedDeadline.length() > 0;
            if (!input.contains (" /by ")) {
                correctFormat = false;
            }
        } catch (IndexOutOfBoundsException e){
            correctFormat = false;
        }
        if (!correctFormat) {
            throw new WrongFormatException("deadline 'Task description' /by 'deadline date'");
        }
        try {
            LocalDate deadline = LocalDate.parse(
                    input.substring(input.indexOf("/by") + 4));
            Task taskDeadline = new Deadline(input, deadline);
            if (tasklist.checkDuplicates(taskDeadline)) {
                return "OOPS! You have added this task before already!";
            }
            tasklist.addTask(taskDeadline);
            return "Got it. I've added this task: \n  " + taskDeadline
                    + "\nNow you have " + tasklist.getSize() + " tasks in the list.";
        } catch (DateTimeParseException e){
            return "Please enter date in the correct format! YYYY-MM-DD, example: 2023-10-10";
        }
    }
}
