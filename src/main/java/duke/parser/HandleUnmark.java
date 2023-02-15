package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.TaskList;

public class HandleUnmark {
    public HandleUnmark() {
    }

    public static String performUnmark(String input, TaskList tasklist) throws WrongFormatException {
        boolean correctFormat = input.matches("unmark\\s\\d+");

        if (!correctFormat) {
            throw new WrongFormatException("unmark 'Task Number'");
        }

        try {
            int taskNum = Integer.parseInt(input.substring(7)) - 1;
            tasklist.unmarkTask(taskNum);
            return "Ok, I've marked this task as not done yet: \n  " + tasklist.getTask(taskNum);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please enter a valid task number!";
        }
    }
}
