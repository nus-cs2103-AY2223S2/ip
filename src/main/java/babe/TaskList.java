package babe;

import babe.task.Deadline;
import babe.task.Event;
import babe.task.Task;
import babe.task.ToDo;

import java.util.ArrayList;

/**
 * The <code>TaskList</code> class represents the list of Tasks in Babe.
 * It contains methods for displaying and manipulating content of this TaskList.
 */
class TaskList {
    /**
     * Number of Tasks currently stored in this Babe.
     */
    private int taskCount = 0;

    /**
     * List of Tasks Babe received from the user.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a ToDo to memory.
     * Calls the ToDo constructor and inserts created ToDo into this Babe's memory.
     *
     * @param content The description of the ToDo item.
     * @return
     */
    protected String addToDo(String content, boolean toMark) {
        ToDo item = new ToDo(content);
        tasks.add(taskCount++, item);
        if (toMark) {
            item.mark();
        }
        return item.toString();
    }

    /**
     * Adds a Deadline to memory.
     * Calls the Deadline constructor and inserts created Deadline into this Babe's memory.
     *
     * @param content The content of the Deadline item.
     * @param date    The date of the deadline. May include time too.
     */
    protected String addDeadline(String content, String date, boolean toMark) {
        Deadline item = new Deadline(content, date);
        tasks.add(taskCount++, item);
        if (toMark) {
            item.mark();
        }
        return item.toString();
    }

    /**
     * Adds an Event to memory.
     * Calls the Event constructor and inserts created Event into this Babe's memory.
     *
     * @param content   The content of the Event item.
     * @param startDate The start date of the Event. May include time too.
     * @param endDate   The end date of the Event. May include time too.
     */
    protected String addEvent(String content, String startDate, String endDate, boolean toMark) {
        Event item = new Event(content, startDate, endDate);
        tasks.add(taskCount++, item);
        if (toMark) {
            item.mark();
        }
        return item.toString();
    }

    /**
     * Deletes Task in memory specified by given index.
     *
     * @param index An integer that represents the index of the Task to be removed from memory.
     */
    protected String deleteTask(int index) {
        assertIndexInRange(index);
        Task removedTask = this.tasks.remove(index - 1);
        taskCount--;
        return removedTask.toString();
    }

    /**
     * Marks the item of given index in Babe's list as Done.
     * This function will extract the index to be marked and sets the index to True in doneStatus.
     *
     * @param index An integer that represents the index of the item to be marked.
     */
    protected String markTask(int index) {
        assertIndexInRange(index);
        Task itemAtIndex = tasks.get(index - 1);
        itemAtIndex.mark();
        return itemAtIndex.toString();
    }

    /**
     * Unmarks the item of given index in Babe's list as Undone.
     * This function will extract the index to be un-marked and sets the index to False in doneStatus.
     *
     * @param index An integer that represents the index of the item to be marked.
     */
    protected String unmarkTask(int index) {
        assertIndexInRange(index);
        Task itemAtIndex = tasks.get(index - 1);
        itemAtIndex.unmark();
        return itemAtIndex.toString();
    }

    /**
     * Find a task in the TaskList that contains the searchKey entirely.
     *
     * @param searchKey A String input by the user.
     * @return An ArrayList containing Strings of all the Tasks found.
     */
    protected ArrayList<String> findTasks(String searchKey) {
        ArrayList<String> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks.get(i);
            if (task.contains(searchKey)) {
                foundTasks.add(task.toString());
            }
        }

        return foundTasks;
    }

    @Override
    /**
     * Implements toString() method for the TaskList.
     */
    public String toString() {
        String string = "";
        for (int i = 0; i < this.taskCount; i++) {
            string += Integer.toString(i + 1) + ". ";
            string += tasks.get(i).toString();
            string += "\n";
        }
        return string;
    }

    /**
     * Returns true if the TaskList has zero items.
     *
     * @return A boolean value. true if Task List has zero items, false otherwise.
     */
    protected boolean isEmpty() {
        return taskCount == 0;
    }

    /**
     * Returns length of the TaskList (i.e. count)
     */
    protected int length() {
        return taskCount;
    }

    /**
     * Returns the item at the specified index from this TaskList.
     */
    protected Task get(int index) {
        return tasks.get(index);
    }

    private void assertIndexInRange(int index) throws AssertionError {
        assert index <= tasks.size() : "Please pick an index from the list, darling.";
    }


}
