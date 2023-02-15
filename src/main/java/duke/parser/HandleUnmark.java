package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.TaskList;
import duke.ui.Ui;

public class HandleUnmark {
    public HandleUnmark() {
    }

    public static String performUnmark(String input, TaskList tasklist, Ui ui) {

        try {
            int taskNum = Integer.parseInt(input.substring(7)) - 1;
            tasklist.unmarkTask(taskNum);
            return ui.showUnmarkTask(tasklist.getTask(taskNum).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please enter a valid task number!";
        }
    }
}
