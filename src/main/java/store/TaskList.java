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
    public void listTask(String[] inputLine) throws DukeException {
        if (inputLine.length > 1) {
            throw new DukeException("Invalid format");
        }
        int number = 1;
        for (Task stored : tasks) {
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
        tasks.add(task);
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
        Task task = tasks.get(index);
        ui.printDeleteTaskMsg(task, size - 1);
        tasks.remove(index);
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
        int size = tasks.size();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = tasks.get(index);
        task.setChecked(isMarked);
        ui.printMarkTaskMsg(isMarked, task);
    }

    /**
     * Finds tasks specified by user.
     *
     * @param inputArr Input from user.
     * @throws DukeException Checks for valid input from user.
     */
    public void findTask(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Invalid format, please give search hint");
        }
        int number = 1;
        for (Task stored : tasks) {
            if (stored.getStr().equals(inputArr[1])) {
                System.out.println(number + ". " + stored.toString());
                number++;
            }
        }

    }

}
