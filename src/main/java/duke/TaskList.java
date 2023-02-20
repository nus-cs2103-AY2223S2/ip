package duke;
import java.util.ArrayList;
import java.util.HashSet;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * List of tasks that the user can add, mark, find or delete.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private HashSet<String> tasksHashSet = new HashSet<>();

    /**
     * Class constructor of Storage.
     * Initialize by adding a zeroth element so that the index of tasks can start from 1.
     */
    public TaskList() {
        tasks.add(new ToDo("zeroth"));
    }

    /**
     * Add a Todo Task to the TaskList.
     * @param title the content of the new Todo Task
     * @return the message that indicates the addition of the new todo task
     * @throws DukeException if the content is empty
     */
    public String addToDo(String title) throws DukeException {
        if (isEmpty(title)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        ToDo newTask = new ToDo(title);
        if (tasksHashSet.contains(newTask.toString())) {
            throw new DukeException("The task already exists!");
        }
        String res = "";
        tasks.add(newTask);
        tasksHashSet.add(newTask.toString());
        res += "Got it. I've added this task:\n";
        res += newTask + "\n";
        res += "Now you have " + (tasks.size() - 1) + " tasks in the list.\n";
        return res;
    }

    /**
     * Add a Deadline Task to the TaskList.
     * @param title the content of the new Deadline Task
     * @param deadline the deadline date and time of the new Deadline Task
     * @return the message that indicates the addition of the new deadline task
     * @throws DukeException if the content is empty
     */
    public String addDeadline(String title, String deadline) throws DukeException {
        if (isEmpty(title) || isEmpty(deadline)) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }
        Deadline newTask = new Deadline(title, deadline);
        if (tasksHashSet.contains(newTask.toString())) {
            throw new DukeException("The task already exists!");
        }
        String res = "";
        tasks.add(newTask);
        tasksHashSet.add(newTask.toString());
        res += "Got it. I've added this task:\n";
        res += newTask + "\n";
        res += "Now you have " + (tasks.size() - 1) + " tasks in the list.\n";
        return res;
    }

    /**
     * Add an Event Task to the TaskList.
     * @param title the content of the new Event Task
     * @param fromDateTime the start date and time of the new Event Task
     * @param toDateTime the end date and time of the new Event Task
     * @return the message that indicates the addition of the new event task
     * @throws DukeException if the content is empty
     */
    public String addEvent(String title, String fromDateTime, String toDateTime) throws DukeException {
        if (title.equals("") || fromDateTime.equals("") || toDateTime.equals("")) {
            throw new DukeException("The description and duration of an event cannot be empty.");
        }
        Event newTask = new Event(title, fromDateTime, toDateTime);
        if (tasksHashSet.contains(newTask.toString())) {
            throw new DukeException("The task already exists!");
        }
        String res = "";
        tasks.add(newTask);
        tasksHashSet.add(newTask.toString());
        res += "Got it. I've added this task:";
        res += newTask + "\n";
        res += "Now you have " + (tasks.size() - 1) + " tasks in the list.\n";
        return res;
    }

    /**
     * Checks whether the string is an empty string, excluding spaces.
     * @param str the string to be checked
     * @return true when the string is empty
     */
    public boolean isEmpty(String str) {
        String temp = str.replaceAll("\\s+", "");
        if (temp.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Deletes a Task from the TaskList.
     * @param index the index of the Task to be deleted
     * @return the message that indicates the deletion of the task.
     */
    public String delete(int index) {
        String res = "";
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        tasksHashSet.remove(removedTask.toString());
        res += "Noted. I've removed this task:\n";
        res += removedTask.toString() + "\n";
        res += "Now you have " + (tasks.size() - 1) + " tasks in the list.\n";
        return res;
    }

    /**
     * Deletes all the tasks in the TaskList.
     * @return the message that indicates the deletion of the tasks.
     */
    public String deleteAll() {
        tasks.clear();
        tasks.add(new ToDo("zeroth"));
        tasksHashSet.clear();
        String res = "Noted. I've removed all the tasks\n";
        return res;
    }

    /**
     * Returns a task with the given index.
     * @param index the Task to be returns
     * @return Task with the given index in the TaskList
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Finds tasks that contains the target string.
     * @param target the target string to find
     * @return the list of tasks that contains the target string in String.
     */
    public String find(String target) {
        String res = "";
        ArrayList<Task> foundTasks = new ArrayList<>();
        ArrayList<Integer> taskIndexes = new ArrayList<>();
        foundTasks.add(new ToDo("zeroth"));
        taskIndexes.add(0);
        res += "Here are the matching tasks in your list:\n";
        for (int i = 1; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().contains(target)) {
                foundTasks.add(tasks.get(i));
                taskIndexes.add(i);
            }
        }
        for (int i = 1; i < foundTasks.size(); i++) {
            res += taskIndexes.get(i) + ". " + foundTasks.get(i).toString() + "\n";
        }
        return res;
    }

    /**
     * Stringifies the TaskList.
     * @return the string representation of the TaskList
     */
    @Override
    public String toString() {
        String str = "Here are the tasks in your list:";
        for (int i = 1; i < tasks.size(); i++) {
            str += "\n";
            str += i + ". " + tasks.get(i).toString();
        }
        return str;
    }

    /**
     * Stringifies the TaskList with relevant format for the saved data file.
     * @return the string representation of the TaskList that follows the format for the saved data file
     */
    public String toTxtString() {
        String data = "";
        for (int i = 1; i < tasks.size(); i++) {
            data += tasks.get(i).toTxtString() + "\n";
        }
        return data;
    }

    /**
     * Returns the number of Tasks in this TaskList.
     * @return the number of Tasks in this TaskList
     */
    public int size() {
        return tasks.size();
    }
}
