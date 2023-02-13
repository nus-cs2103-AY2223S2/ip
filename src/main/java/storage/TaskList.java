package storage;

import dukeexception.DukeException;
import task.Task;
import userinteraction.Ui;

import java.util.ArrayList;

/**
 * Task list class which stores all tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Class constructor.
     *
     * @param tasks Arraylist that stores all tasks.
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
     * List all the tasks.
     *
     * @param input User input.
     * @throws DukeException Checks the validation of input.
     */
    public void listTask(String input) throws DukeException {
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length > 1) {
            throw new DukeException("\t ☹ OOPS!!! The format is invalid!\n");
        }
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str = "\t " + (i + 1) + ". " + tasks.get(i).toString();
            System.out.println(str);
        }
        System.out.print("");
    }

    /**
     * Adds a task into task list.
     *
     * @param task A task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in the task list.
     *
     * @param input User input
     * @param ui    The Ui to be used for printing messages.
     * @param storage Deletes a task in a file.
     * @throws DukeException Checks the validation of input.
     */
    public void deleteTask(String input, Ui ui, Storage storage) throws DukeException {
        if (input.trim().equals("delete")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a delete cannot be empty.\n");
        }
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length < 2) {
            throw new DukeException("\t ☹ OOPS!!! The format is invalid, please give numbers.\n");
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(inputLine[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("\t ☹ OOPS!!! Please input a valid number.\n");
        }
        int taskListSize = this.getSize();
        if (taskIndex < 1 || taskIndex > taskListSize) {
            throw new DukeException("\t ☹ OOPS!!! The index is out of range.\n");
        }
        Task task = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        storage.saveData(this);
        ui.printDeleteTaskMsg(task, tasks.size());
    }

    /**
     * Marks or unmarks a task.
     *
     * @param isDone Boolean to mark or unmark a task.
     * @param input  User Input.
     * @param ui     The Ui to be used for printing messages.
     * @throws DukeException Checks the validation of input.
     */
    public void markTask(boolean isDone, String input, Ui ui, Storage storage) throws DukeException {
        if (input.trim().equals("mark") || input.trim().equals("unmark")) {
            throw new DukeException("\t ☹ OOPS!!! Please input a number.\n");
        }
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length < 2) {
            throw new DukeException("\t ☹ OOPS!!! The format is invalid, please give numbers.\n");
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(inputLine[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("\t ☹ OOPS!!! Please input a valid number.\n");
        }
        int taskListSize = tasks.size();
        if (taskIndex + 1 > taskListSize || taskIndex < 0) {
            throw new DukeException("\t ☹ OOPS!!! The index is out of range.\n");
        }
        Task task = tasks.get(taskIndex);
        task.setDone(isDone);
        storage.saveData(this);
        ui.printMarkTaskMsg(isDone, task);
    }

    public void findTask(String input, Ui ui) throws DukeException {
        if (input.trim().equals("find")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a find cannot be empty.\n");
        }
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length < 2) {
            throw new DukeException("\t ☹ OOPS!!! The format is invalid, please give a search information.\n");
        }
        ui.printFindTaskMsg();
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(inputLine[1])) {
                str = "\t " + (i + 1) + ". " + tasks.get(i).toString();
                System.out.println(str);
            }
        }
    }
}