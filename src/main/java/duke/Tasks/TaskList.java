package duke.Tasks;

import duke.Exceptions.CommandException;
import duke.Exceptions.DescriptionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructor for list of tasks
     */
    public TaskList() {
        super();
    }

    /**
     * Instantiates an array list of tasks and filling with tasks from buffered reader
     * @param strTasks buffered reader with all the tasks in string
     * @throws CommandException if there are problems with converting the strings to a task
     * @throws IOException if the buffered reader is not working as intended
     */
    public TaskList(BufferedReader strTasks) throws CommandException, IOException {
        super();
        String str;
        while ((str = strTasks.readLine()) != null) {
            this.add(Task.strToTask(str));
        }
    }

    /**
     * String representation of task list
     * @return string representation of task list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Task task : this) {
            result.append(task).append("\n");
        }
        return result.toString();
    }

    /**
     * Better string representation of task list including the order and index numbers
     * @return Better string representation of task list
     */
    public String toFormattedString() {
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : this) {
            result.append(count++)
                    .append(". ")
                    .append(task)
                    .append("\n");
        }
        return result.toString();
    }

    public void addTask(Task task) throws DescriptionException {
        if (task.isEmpty()) {
            throw new DescriptionException();
        } else {
            this.add(task);
        }
    }

    public Task markTask(int index) throws IndexOutOfBoundsException {
        Task task = this.get(index);
        task.markDone();
        return task;
    }
}
