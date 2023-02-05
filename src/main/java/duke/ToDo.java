package duke;

import util.DukeException;
import util.TaskList;

/**
 * Subclass of Task which stores the taskName
 *
 * @author Merrick
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo
     * @param taskName Description of ToDo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructor for ToDo
     * @param taskName Description of ToDo task.
     * @param completion Completion status of ToDo task.
     */
    public ToDo(String taskName, boolean completion) {
        super(taskName, completion);
    }


    /**
     * Static method to create a ToDo Task from user input.
     * @param command User input to be used to create the ToDo Task.
     * @param t TaskList object to add the newly created Task into the list of tasks.
     * @throws DukeException If command is invalid.
     */
    public static String createToDo(String command, TaskList t) throws DukeException {
        String[] input = command.split(" ");
        if (input.length <= 1) {
            throw new DukeException("todo");
        }
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            taskName.append(input[i]);
            if (i < input.length - 1) {
                taskName.append(" ");
            }
        }
        ToDo todo = new ToDo(taskName.toString());
        return t.addTask(todo);
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }

}
