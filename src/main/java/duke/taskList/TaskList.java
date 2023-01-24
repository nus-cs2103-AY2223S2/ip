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
    public void addTask(Command type, String[] contents) throws DukeException {
        switch (type) {
        case TODO:
            System.out.println("Got it. I've added this task:");
            list.add(new Todo(contents[0]));
            System.out.println("    " + list.get(list.size() - 1).toString());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            break;
        case DEADLINE:
            list.add(new Deadline(contents[0], contents[1]));
            System.out.println("Got it. I've added this task:");
            System.out.println("    " + list.get(list.size() - 1).toString());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            break;
        case EVENT:
            list.add(new Event(contents[0], contents[1], contents[2]));
            System.out.println("Got it. I've added this task:");
            System.out.println("    " + list.get(list.size() - 1).toString());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            break;
        default:
            break;
        }
    }

    /**
     * Deletes a task at a specified index.
     * @param contents The index provided.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public void deleteTask(String[] contents) throws DukeException {
        // Converts provided index to respective ArrayList index.
        int index = Integer.parseInt(contents[1].replaceAll("[^0-9]", "")) - 1;
        if ((index < 0) | (index > (list.size() - 1))) {
            // Checks if provided index is in range.
            throw new DukeException("index");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println("    " + list.get(index).toString());
            list.remove(index);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    /**
     * Marks a task at a specified index as done.
     *
     * @param contents The index provided.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public void markTask(String[] contents) throws DukeException {
        // Converts provided index to respective ArrayList index.
        int index = Integer.parseInt(contents[1].replaceAll("[^0-9]", "")) - 1;
        if ((index < 0) | (index > (list.size() - 1))) {
            // Checks if provided index is in range.
            throw new DukeException("index");
        } else {
            list.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + list.get(index).toString());
        }
    }

    /**
     * Marks a task at a specified index as undone.
     *
     * @param contents The index provided.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public void unmarkTask(String[] contents) throws DukeException {
        // Converts provided index to respective ArrayList index.
        int index = Integer.parseInt(contents[1].replaceAll("[^0-9]", "")) - 1;
        if ((index < 0) | (index > (list.size() - 1))) {
            // Checks if provided index is in range.
            throw new DukeException("index");
        } else {
            list.get(index).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("    " + list.get(index).toString());
        }
    }

    /**
     * Lists all the tasks currently in the list,
     * enumerated starting from 1.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
    }

    /**
     * Lists all tasks that matches the specified input.
     * @param contents The input to match.
     * @throws DukeException If the index is out of bounds, an exception is thrown.
     */
    public void findTasks(String[] contents) throws DukeException {
        // Counter for the number of matching tasks.
        int taskCount = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(contents[1])) {
                taskCount++;
                if (taskCount == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println(taskCount + ". " + list.get(i).toString());
            }
        }
        if (taskCount == 0) {
            System.out.println("No tasks matching the given input was found.");
        }
    }
}
