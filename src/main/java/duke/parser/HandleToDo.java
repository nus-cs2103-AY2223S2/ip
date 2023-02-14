package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class HandleToDo {
    public HandleToDo() {
    }

    public static String performToDo(String input, TaskList tasklist) throws WrongFormatException {
        boolean correctFormat = input.split("").length > 1;

        if (!correctFormat) {
            throw new WrongFormatException("todo 'Task description'");
        }

        Task taskToDo = new ToDo(input);
        tasklist.addTask(taskToDo);
        return "Got it. I've added this task: \n  " + taskToDo
                + "\nNow you have " + tasklist.getSize() + " tasks in the list.";
    }
}
