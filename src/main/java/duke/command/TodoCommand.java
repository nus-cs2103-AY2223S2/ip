package duke.command;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * A command to create todo activities.
 */
public class TodoCommand {
    /**
     * Creates a todo object that is added to list of existing tasks.
     * @param tasks the list of tasks.
     * @param input user's input describing the todo activity.
     * @return acknowledgement of the task being successfully added.
     * @throws DukeException if an invalid description were found.
     */
    public static String addTodo(TaskList tasks, String input) throws DukeException {
        if (input.length() < 6 || !input.matches("todo\\s.*")) {
            throw new DukeException("The description of a <todo> cannot be empty!");
        }
        String info = input.substring(5).trim();
        Task task = new ToDo(info);
        tasks.addTask(task);
        return "Got it. I've added this task:\n\t"
                + task.getStatusIcon()
                + "\n" + "Now you have "
                + tasks.getSize()
                + " task(s) in the list.";
    }
}
