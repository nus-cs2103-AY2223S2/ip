package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class HandleToDo {
    public HandleToDo() {
    }

    public static String performToDo(String input, TaskList tasklist, Ui ui) throws WrongFormatException {
        boolean correctFormat = input.split("").length > 1;

        if (!correctFormat) {
            throw new WrongFormatException("todo 'Task description'");
        }

        Task taskToDo = new ToDo(input);
        if (tasklist.checkDuplicates(taskToDo)) {
            return ui.showError("OOPS! You have added this task before already!");
        }
        tasklist.addTask(taskToDo);
        return ui.showAddTask(taskToDo.toString(), tasklist.getSize());
    }
}
