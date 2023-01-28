package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;

import java.util.ArrayList;

/**
 * This class represents the task operations.
 */
public class TaskList {
    /** Array list of tasks */
    private ArrayList<Task> listOfTasks;

    /** Deals with interactions with the user */
    private final Ui ui = new Ui();

    /**
     * Initializes the list of tasks.
     *
     * @param listOfTasks Array list of tasks.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public TaskList() {
        this.listOfTasks = new ArrayList<>(100);
    }

    /**
     * Saves the task to the list of tasks
     * and prints it.
     *
     * @param description Description of the todo task.
     */
    public void saveTask(String description) {
        Task task = new Todo(description);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    /**
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public void saveTask(String description, LocalDateTime by) {
        Task task = new Deadline(description, by);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    /**
     * @param description Description of the event task.
     * @param from Start date time of the event.
     * @param to End date time of the event.
     */
    public void saveTask(String description, LocalDateTime from, LocalDateTime to) {
        Task task = new Event(description, from, to);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    /**
     * Marks the task as done and prints it.
     *
     * @param index Index of the array list of tasks.
     * @throws DukeException If index of the list of tasks is out of bounds.
     */
    public void markTask(int index) throws DukeException {
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            ui.printMarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    /**
     * Marks the task as undone and prints it.
     *
     * @param index Index of the array list of tasks.
     * @throws DukeException If index of the list of tasks is out of bounds.
     */
    public void unmarkTask(int index) throws DukeException {
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsUndone();
            ui.printUnmarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    /**
     * Deletes the task and prints it.
     *
     * @param index Index of the array list of tasks.
     * @throws DukeException If index of the list of tasks is out of bounds.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            ui.printDeleteTask(task, listOfTasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public void findTask(String description) {
        ArrayList<Task> matchedListOfTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if (task.toString().indexOf(description) > 0) {
                matchedListOfTasks.add(task);
            }
        }
        ui.printMatchingTasks(matchedListOfTasks);
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
}
