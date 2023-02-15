package duke.parser;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class HandleDelete {
    public HandleDelete() {
    }

    public static String performDelete(String input, TaskList tasklist, Ui ui) {

        try {
            int taskNum = Integer.parseInt(input.substring(7)) - 1;
            Task taskToRemove = tasklist.getTask(taskNum);
            String removedTaskStr = taskToRemove.toString();
            tasklist.deleteTask(taskNum);
            return ui.showDeleteTask(removedTaskStr, tasklist.getSize());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError("Please enter a valid task number!");
        }
    }

}
