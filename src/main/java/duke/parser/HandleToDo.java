package duke.parser;

import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Processes and handle the todo command
 */
public class HandleToDo {
    public HandleToDo() {
    }

    /**
     * Check whether the input line to add a todo task is valid and in the correct format or not
     * Perform the adding of todo task into task list if input is in correct format
     * @param input Entered by user
     * @param tasklist List of existing tasks
     * @param ui Ui that would generate reply for the user
     * @return A String to respond to user through ui, inform user whether task has been added or not
     * @throws WrongFormatException This exception is thrown when input is not in correct format
     */
    public static String performToDo(String input, TaskList tasklist, Ui ui) throws WrongFormatException {
        String taskString;
        try {
            taskString = input.trim().substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new WrongFormatException("todo <Task description>");
        }

        Task taskToDo = new ToDo(taskString);
        if (tasklist.checkDuplicates(taskToDo)) {
            return ui.showError("OOPS! You have added this task before already!");
        }

        tasklist.addTask(taskToDo);
        return ui.showAddTask(taskToDo.toString(), tasklist.getSize());
    }
}
