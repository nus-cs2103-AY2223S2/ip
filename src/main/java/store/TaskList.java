package store;

import java.util.ArrayList;

import dukeexception.DukeException;
import task.Task;
import userinteraction.Ui;

/**
 * Stores all tasks in an arrayList.
 */
public class TaskList {
    /**
     * ArrayList that stores all tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Public constructor.
     *
     * @param tasks ArrayList that stores all tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public int getSize() {
        return tasks.size();
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Lists all tasks in the arrayList.
     *
     * @param inputLine User input.
     * @throws DukeException Checks valid input from user.
     */
    public String listTask(String[] inputLine) throws DukeException {
        if (inputLine.length > 1) {
            throw new DukeException("Invalid format");
        }
        assert inputLine.length == 1;
        int number = 1;
        StringBuilder output = new StringBuilder();
        for (Task stored : tasks) {
            output.append(number).append(". ").append(stored.toString()).append("\n");
            number++;
        }
        return output.toString();
    }

    /**
     * Adds task to arrayList.
     *
     * @param task Task from user.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task from arrayList.
     *
     * @param inputArr Input from user.
     * @param ui Handles all user interaction.
     * @throws DukeException Checks valid input from user.
     */
    public String deleteTask(String[] inputArr, Ui ui, Storage storage) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Invalid format, please give numbers");
        }
        assert inputArr.length == 2;
        int index = Integer.parseInt(inputArr[1]) - 1;
        int size = this.getSize();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        storage.writeData(this);
        return ui.getDeleteTaskMsg(task, size - 1);
    }

    /**
     * Marks or unmarks task specified by user.
     *
     * @param isMarked Boolean to mark or unmark task.
     * @param input Input from user.
     * @param ui Handles all user interaction.
     * @throws DukeException Checks valid input from user.
     */
    public String markTask(boolean isMarked, String[] input, Ui ui, Storage storage) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Invalid format, please give numbers");
        }
        assert input.length == 2;
        int index = Integer.parseInt(input[1]) - 1;
        int size = tasks.size();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = tasks.get(index);
        task.setChecked(isMarked);
        storage.writeData(this);
        return ui.getMarkTaskMsg(isMarked, task);
    }

    /**
     * Finds tasks specified by user.
     *
     * @param inputArr Input from user.
     * @throws DukeException Checks for valid input from user.
     */
    public String findTask(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Invalid format, please give search hint");
        }
        assert inputArr.length == 2;
        int number = 1;
        StringBuilder output = new StringBuilder();
        for (Task stored : tasks) {
            if (stored.getStr().equals(inputArr[1])) {
                output.append(number).append(". ").append(stored.toString()).append("\n");
                number++;
            }
        }
        return output.toString();

    }

}
