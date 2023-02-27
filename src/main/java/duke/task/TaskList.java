package duke.task;

import duke.Storage;
import duke.exception.CommandException;
import duke.exception.DescriptionException;
import duke.exception.FileException;

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
     * @throws FileException if the buffered reader is not working as intended eg file is corrupted and cannot be read
     */
    public TaskList(BufferedReader strTasks) throws FileException, CommandException {
        super();
        String str;
        try {
            while ((str = strTasks.readLine()) != null) {
                this.add(Task.strToTask(str));
            }
        } catch (IOException ioException) {
            throw new FileException();
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

    public void addTask(Task task, Storage storage) throws DescriptionException, FileException {
        assert !task.isEmpty();
        if (task.isEmpty()) {
            throw new DescriptionException();
        } else {
            this.add(task);
        }
        storage.store(this);
    }

    public Task remove(int index, Storage storage) throws FileException{
        Task output = super.remove(index);
        storage.store(this);
        return output;
    }

    public Task markTask(int index) throws IndexOutOfBoundsException {
        Task task = this.get(index);
        task.markDone();
        return task;
    }
}
