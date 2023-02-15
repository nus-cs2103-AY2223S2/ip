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
        String taskString;
        try {
            taskString = input.trim().substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new WrongFormatException("todo 'Task description'");
        }

        Task taskToDo = new ToDo(taskString);
        if (tasklist.checkDuplicates(taskToDo)) {
            return ui.showError("OOPS! You have added this task before already!");
        }
        tasklist.addTask(taskToDo);
        return ui.showAddTask(taskToDo.toString(), tasklist.getSize());
    }
}
