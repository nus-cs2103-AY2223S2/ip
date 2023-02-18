package tigerlily.tasks;

import tigerlily.exceptions.DukeExceptions;
import tigerlily.exceptions.InvalidIndexException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList when there is no pre-existing TaskList
     */
    public TaskList() {

    }

    /**
     * Constructor for TaskList when there is a pre-existing TaskList
     * @param tasks the ArrayList of pre-existing Tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList of Tasks stored in a TaskList.
     *
     * @return the ArrayList of Tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a given Task into the TaskList.
     *
     * @param task the new Task to be added into the TaskList
     */
    public void addTask(Task task) {
        assert task != null : "Null Task input";
        tasks.add(task);
    }

    /**
     * Deletes the Task at the given index in the TaskList.
     *
     * @param input the input command, which is parsed to find the index
     * @return the Task which was deleted
     * @throws DukeExceptions If the given index is out of bounds
     */
    public Task deleteTask(String input) throws DukeExceptions {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= this.getSize()) {
            throw new InvalidIndexException();
        } else {
            Task thisTask = tasks.get(index);
            tasks.remove(index);
            return thisTask;
        }
    }

    /**
     * Generates the String representation of the TaskList.
     *
     * @return the String representation of the TaskList
     */
    public String printList() {
        StringBuilder sb = new StringBuilder("here's your list:\n");
        for (int i = 0; i < this.getSize(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i).toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Returns the number of Tasks in the TaskList.
     *
     * @return the number of Tasks in the TaskList
     */
    public int getSize() {
        return tasks.size();
    }
}