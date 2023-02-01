import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

/**
 * This class contains the task list.
 * It has operations to add/delete tasks in the list
 */
public class TaskList {
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
     * @param content The description of the ToDo item.
     */
    public Task addToDo(String content, boolean toNotify) {
        ToDo item = new ToDo(content);
        tasks.add(taskCount++, item);
        if (toNotify) {
            Ui.notifyAddTask(item, taskCount);
        }
        return item;
    }

    /**
     * Adds a Deadline to memory.
     * Calls the Deadline constructor and inserts created Deadline into this Babe's memory.
     * @param content The content of the Deadline item.
     * @param date The date of the deadline. May include time too.
     */
    public Task addDeadline(String content, String date, boolean toNotify) {
        Deadline item = new Deadline(content, date);
        tasks.add(taskCount++, item);
        if (toNotify) {
            Ui.notifyAddTask(item, taskCount);
        }
        return item;
    }

    /**
     * Adds an Event to memory.
     * Calls the Event constructor and inserts created Event into this Babe's memory.
     * @param content The content of the Event item.
     * @param startDate The start date of the Event. May include time too.
     * @param endDate The end date of the Event. May include time too.
     */
    public Task addEvent(String content, String startDate, String endDate, boolean toNotify) {
        Event item = new Event(content, startDate, endDate);
        tasks.add(taskCount++, item);
        if (toNotify) {
            Ui.notifyAddTask(item, taskCount);
        }
        return item;
    }

    /**
     * Deletes Task in memory specified by given index.
     *
     * @param index An integer that represents the index of the Task to be removed from memory.
     */
    public void deleteTask(int index) {
        Task removedTask = this.tasks.remove(index - 1);
        Ui.notifyDelete(removedTask, --taskCount);
    }

    /**
     * Marks/Unmarks the item of given index in Babe's list as Done/Undone.
     * If user keys in "mark", this function will extract the index to be marked and sets the index to True in
     * doneStatus. Sets the index to False if "unmark"is keyed in.
     */
    public void changeStatus(boolean toMark, int index) {
        Task itemAtIndex = tasks.get(index - 1);
        if (toMark) {
            itemAtIndex.mark();
        } else {
            itemAtIndex.unmark();
        }
        Ui.notifyStatusChanged(itemAtIndex, toMark);
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
     * @return A boolean value. true if Task List has zero items, false otherwise.
     */
    public boolean isEmpty() {
        return taskCount == 0;
    }

    /**
     * Returns length of the TaskList (i.e. count)
     */
    public int length() {
        return taskCount;
    }

    /**
     * Returns the item at the specified index from this TaskList.
     */
    public Task get(int index) {
        return tasks.get(index);
    }


}
