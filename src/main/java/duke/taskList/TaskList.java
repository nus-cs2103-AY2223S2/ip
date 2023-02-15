package duke.tasklist;

import java.util.ArrayList;

import duke.dukeexception.DukeException;
import duke.parser.Parser.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * TaskList object that contains all the tasks currently
 * in the task list.
 */
public class TaskList {
    private static final String TASK_ADDED = "Got it. I've added this task:";
    /** Arraylist to store the tasks */
    private ArrayList<Task> list;

    /**
     * Constructor for the Duke.TaskList class.
     *
     * @param list the list that will be used.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds the task to the list.
     *
     * @param type The type of task to be added.
     * @param contents The details of the task.
     * @throws DukeException
     */
    public String addTask(Command type, String[] contents) throws DukeException {
        String output;
        switch (type) {
        case TODO:
            list.add(new Todo(contents[0]));
            break;
        case DEADLINE:
            list.add(new Deadline(contents[0], contents[1]));
            break;
        case EVENT:
            list.add(new Event(contents[0], contents[1], contents[2]));
            break;
        default:
            throw new DukeException("unknown");
        }
        output = TASK_ADDED;
        output += "\n    " + list.get(list.size() - 1).toString();
        output += "\nNow you have " + list.size() + " tasks in the list.";
        return output;
    }

    /**
     * Deletes a task at a specified index.
     * @param contents The index provided.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public String deleteTask(String[] contents) throws DukeException {
        // Converts provided index to respective ArrayList index.
        int index = Integer.parseInt(contents[1].replaceAll("[^0-9]", "")) - 1;
        String output;
        if ((index < 0) | (index > (list.size() - 1))) {
            // Checks if provided index is in range.
            throw new DukeException("index");
        } else {
            output = "Meow-ted. I've removed this task:";
            output += "\n    " + list.get(index).toString();
            list.remove(index);
            output += "\nNow you have " + list.size() + " tasks in the list.";
            return output;
        }
    }

    /**
     * Marks a task at a specified index as done.
     *
     * @param contents The index provided.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public String markTask(String[] contents) throws DukeException {
        // Converts provided index to respective ArrayList index.
        int index = Integer.parseInt(contents[1].replaceAll("[^0-9]", "")) - 1;
        String output;
        if ((index < 0) | (index > (list.size() - 1))) {
            // Checks if provided index is in range.
            throw new DukeException("index");
        } else {
            list.get(index).markAsDone();
            output = "Nice! I've marked this task as done:";
            output += "\n    " + list.get(index).toString();
            return output;
        }
    }

    /**
     * Marks a task at a specified index as undone.
     *
     * @param contents The index provided.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public String unmarkTask(String[] contents) throws DukeException {
        // Converts provided index to respective ArrayList index.
        int index = Integer.parseInt(contents[1].replaceAll("[^0-9]", "")) - 1;
        String output;
        if ((index < 0) | (index > (list.size() - 1))) {
            // Checks if provided index is in range.
            throw new DukeException("index");
        } else {
            list.get(index).markAsUndone();
            output = "Meow-k, I've marked this task as not done yet:";
            output += "\n    " + list.get(index).toString();
            return output;
        }
    }

    /**
     * Lists all the tasks currently in the list,
     * enumerated starting from 1.
     */
    public String listTasks() {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            output += "\n" + (i + 1) + ". " + list.get(i).toString();
        }
        return output;
    }

    /**
     * Lists all tasks that matches the specified input.
     * @param contents The input to match.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public String findTasks(String[] contents) throws DukeException {
        // Counter for the number of matching tasks.
        int taskCount = 0;
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(contents[1])) {
                taskCount++;
                assert taskCount <= list.size() : "task count exceeded";
                if (taskCount == 1) {
                    output = "Here are the matching tasks in your list:";
                }
                output += "\n" + taskCount + ". " + list.get(i).toString();
            }
        }
        if (taskCount == 0) {
            return "No tasks matching the given input was found.";
        }
        return output;
    }

    /**
     * Updates the description of the task.
     * @param contents The index and new description provided.
     * @return The string representation of the updated task.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public String updateTask(String[] contents) throws DukeException {
        // Converts provided index to respective ArrayList index.
        int index = Integer.parseInt(contents[1].replaceAll("[^0-9]", "")) - 1;
        String output;
        if ((index < 0) | (index > (list.size() - 1))) {
            // Checks if provided index is in range.
            throw new DukeException("index");
        } else {
            list.get(index).updateDescription(contents[2]);
            output = "Meow-k, I've updated the description of this task:";
            output += "\n    " + list.get(index).toString();
            return output;
        }
    }
}
