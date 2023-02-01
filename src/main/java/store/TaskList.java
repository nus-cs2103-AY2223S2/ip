package store;

import dukeexception.DukeException;
import task.Task;
import userinteraction.Ui;

import java.util.ArrayList;

/**
 * Stores all tasks in an arrayList.
 */
public class TaskList {
    /**
     * ArrayList that stores all tasks.
     */
    private final ArrayList<Task> store;

    /**
     * Public constructor.
     *
     * @param store ArrayList that stores all tasks.
     */
    public TaskList(ArrayList<Task> store) {
        this.store = store;
    }
    public int getSize() {
        return store.size();
    }
    public ArrayList<Task> getStore() {
        return store;
    }

    /**
     * Lists all tasks in the arrayList.
     *
     * @param inputLine User input.
     * @throws DukeException Checks valid input from user.
     */
    public void listTask(String[] inputLine) throws DukeException {
        if (inputLine.length > 1) {
            throw new DukeException("Invalid format");
        }
        int number = 1;
        for (Task stored : store) {
            System.out.println(number + ". " + stored.toString());
            number++;
        }
    }

    /**
     * Adds task to arrayList.
     *
     * @param task Task from user.
     */
    public void addTask(Task task) {
        store.add(task);
    }

    /**
     * Deletes task from arrayList.
     *
     * @param inputArr Input from user.
     * @param ui Handles all user interaction.
     * @throws DukeException Checks valid input from user.
     */
    public void deleteTask(String[] inputArr, Ui ui) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Invalid format, please give numbers");
        }
        int index = Integer.parseInt(inputArr[1]) - 1;
        int size = this.getSize();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = store.get(index);
        ui.printDeleteTaskMsg(task, size - 1);
        store.remove(index);
    }

    /**
     * Marks or unmarks task specified by user.
     *
     * @param isMarked Boolean to mark or unmark task.
     * @param input Input from user.
     * @param ui Handles all user interaction.
     * @throws DukeException Checks valid input from user.
     */
    public void markTask(boolean isMarked, String[] input, Ui ui) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Invalid format, please give numbers");
        }
        int index = Integer.parseInt(input[1]) - 1;
        int size = store.size();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = store.get(index);
        task.setChecked(isMarked);
        ui.printMarkTaskMsg(isMarked, task);
    }

}
