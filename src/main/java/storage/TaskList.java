package storage;

import java.util.ArrayList;

import dukeexception.DukeException;
import task.Task;
import userinteraction.Ui;

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
     * @return Returns a list of tasks are stored.
     * @throws DukeException Checks the validation of input.
     */
    public String listTask(String input) throws DukeException {
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length > 1) {
            throw new DukeException("\t OOPS!!! The format is invalid!\n");
        }
        assert inputLine.length == 1;
        String str = "Current tasks are: \n";
        for (int i = 0; i < tasks.size(); i++) {
            str += "\t " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        if (tasks.size() == 0) {
            return "There is no task yet!";
        } else {
            return str;
        }
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
     * @return Returns delete message of specific task.
     * @throws DukeException Checks the validation of input.
     */
    public String deleteTask(String input, Ui ui, Storage storage) throws DukeException {
        if (input.trim().equals("delete")) {
            throw new DukeException("\t OOPS!!! The description of a delete cannot be empty.\n");
        }
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length < 2) {
            throw new DukeException("\t OOPS!!! The format is invalid, please give numbers.\n");
        }
        assert inputLine.length == 2;
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(inputLine[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("\t OOPS!!! Please input a valid number.\n");
        }
        int taskListSize = this.getSize();
        if (taskIndex < 1 || taskIndex > taskListSize) {
            throw new DukeException("\t OOPS!!! The index is out of range.\n");
        }
        Task task = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        storage.saveData(this);
        return ui.printDeleteTaskMsg(task, tasks.size());
    }

    /**
     * Marks or unmarks a task.
     *
     * @param isDone Boolean to mark or unmark a task.
     * @param input  User Input.
     * @param ui     The Ui to be used for printing messages.
     * @return Returns mark or unmark task message.
     * @throws DukeException Checks the validation of input.
     */
    public String markTask(boolean isDone, String input, Ui ui, Storage storage) throws DukeException {
        if (input.trim().equals("mark") || input.trim().equals("unmark")) {
            throw new DukeException("\t OOPS!!! Please input a number.\n");
        }
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length < 2) {
            throw new DukeException("\t OOPS!!! The format is invalid, please give numbers.\n");
        }
        assert inputLine.length == 2;
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(inputLine[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("\t OOPS!!! Please input a valid number.\n");
        }
        int taskListSize = tasks.size();
        if (taskIndex + 1 > taskListSize || taskIndex < 0) {
            throw new DukeException("\t OOPS!!! The index is out of range.\n");
        }
        Task task = tasks.get(taskIndex);
        task.setDone(isDone);
        storage.saveData(this);
        return ui.printMarkTaskMsg(isDone, task);
    }

    /**
     * Finds the tasks which contains the same input descriptions.
     *
     * @param input User input
     * @param ui    The Ui to be used for printing messages.
     * @return Returns a list of tasks whose contain the input.
     * @throws DukeException Checks the validation of input.
     */
    public String findTask(String input, Ui ui) throws DukeException {
        if (input.trim().equals("find")) {
            throw new DukeException("OOPS!!! The description of a find cannot be empty.\n");
        }
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length < 2) {
            throw new DukeException("OOPS!!! The format is invalid, please give a search information.\n");
        }
        assert inputLine.length == 2;
        boolean availableTasks = false;
        String str = ui.printFindTaskMsg();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(inputLine[1])) {
                availableTasks = true;
                str += "\t " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        if (availableTasks) {
            return str;
        } else {
            return "There is no matched tasks!";
        }
    }
}
