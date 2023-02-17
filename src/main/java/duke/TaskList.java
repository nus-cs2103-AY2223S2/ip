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
    private static final int MAX_SIZE = 100;

    /**
     * Initializes the list of tasks to read,
     * write and save tasks.
     *
     * @param listOfTasks Array list of tasks.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public TaskList() {
        this.listOfTasks = new ArrayList<>(MAX_SIZE);
    }

    /**
     * Saves the task to the list of tasks
     * and prints it.
     *
     * @param ui User interface to interact with the user.
     * @param description Description of the todo task.
     */
    public void saveTask(Ui ui, String description) {
        Task task = new Todo(description);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    /**
     * @param ui User interface to interact with the user.
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public void saveTask(Ui ui, String description, LocalDateTime by) {
        Task task = new Deadline(description, by);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    /**
     * @param ui User interface to interact with the user.
     * @param description Description of the event task.
     * @param from Start date time of the event.
     * @param to End date time of the event.
     */
    public void saveTask(Ui ui, String description, LocalDateTime from, LocalDateTime to) {
        Task task = new Event(description, from, to);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    /**
     * Marks the task as done and prints it.
     *
     * @param ui User interface to interact with the user.
     * @param index Index of the array list of tasks.
     */
    public void markTask(Ui ui, int index) {
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            ui.printMarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexError(index);
        }
    }

    /**
     * Marks the task as undone and prints it.
     *
     * @param ui User interface to interact with the user.
     * @param index Index of the array list of tasks.
     */
    public void unmarkTask(Ui ui, int index) {
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsUndone();
            ui.printUnmarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexError(index);
        }
    }

    /**
     * Deletes the task and prints it.
     *
     * @param ui User interface to interact with the user.
     * @param index Index of the array list of tasks.
     */
    public void deleteTask(Ui ui, int index) {
        try {
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            ui.printDeleteTask(task, listOfTasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexError(index);
        }
    }

    /**
     * Finds and prints the tasks with the matching keyword.
     *
     * @param ui User interface to interact with the user.
     * @param keyword Keyword of the task.
     */
    public void findTask(Ui ui, String keyword) {
        ArrayList<Task> matchedListOfTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            boolean isMatchingTask = task.toString().indexOf(keyword) > 0;
            if (isMatchingTask) {
                matchedListOfTasks.add(task);
            }
        }
        String s = "Here are the matching tasks in your list:";
        ui.listTasks(matchedListOfTasks, s);
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
}
