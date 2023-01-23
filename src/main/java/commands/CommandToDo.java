package commands;

import java.util.Scanner;

import features.DukeException;
import features.TaskList;
import features.Ui;
import tasks.Task;
import tasks.ToDo;

/**
 * Handles 'todo' command.
 */
public class CommandToDo extends Command {
    @Override
    public TaskList handle(Scanner userScan, TaskList taskList) throws DukeException {
        Ui ui = new Ui();
        String toDoName = userScan.nextLine();
        // ERROR: To-Do description is blank.
        if (toDoName.strip().length() == 0) {
            throw new DukeException(ui.formatLogicError("Tasks.ToDo description cannot be blank."));
        }
        Task taskToAdd = new ToDo(toDoName);
        taskList.add(new ToDo(toDoName));
        ui.print("Task added:\n " + taskToAdd + "\n" + "There are now " + taskList.size()
                + " task(s) in your list.");
        return taskList;
    }
}
