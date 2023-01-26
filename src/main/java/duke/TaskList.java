package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * Represents the list of task user has created, or loaded from file.
     */
    List<Task> list;

    /**
     * Constructor to create an instance of Tasklist
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Method to add task into the Duke program
     *
     * @param t Task object representating task created
     */
    public void addTask(Task t) {
        this.list.add(t);
        System.out.println("Got it. I've added this task: ");
        t.printStatus();
        printCount();
    }

    /**
     * Method to assist loading task from file
     *
     * @param t Task object read from file
     */
    public void loadTask(Task t) {
        list.add(t);
    }

    /**
     * Method to remove task from the Duke program
     *
     * @param index Index of task in TaskList
     */
    public void removeTask(int index) {
        this.list.remove(index);
    }

    /**
     * Method to retrieve task from the TaskList
     *
     * @param index Index of task in TaskList
     * @return Task Object
     * @throws taskNotFoundException
     */
    public Task getTask(int index) throws taskNotFoundException {
        try {
            return this.list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new taskNotFoundException();
        }
    }

    /**
     * Method to calculate number of task in TaskList
     *
     * @return Int representation of count of Tasks in TaskList
     */
    public int count() {
        return this.list.size();
    }

    /**
     * Method to display count
     */
    public void printCount() {
        System.out.println("Now you have " + count() + " task(s) in the list.");
    }
}
