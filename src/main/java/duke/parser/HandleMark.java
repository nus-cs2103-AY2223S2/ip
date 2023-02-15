package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.TaskList;
import duke.ui.Ui;

public class HandleMark {
    public HandleMark() {
    }

    public static String performMark(String input, TaskList tasklist, Ui ui) {

        try {
            int taskNum = Integer.parseInt(input.substring(5)) - 1;
            tasklist.markTask(taskNum);
            return ui.showMarkTask(tasklist.getTask(taskNum).toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError("Please enter a valid task number!");
        }
    }
}
