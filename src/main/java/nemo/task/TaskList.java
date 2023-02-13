package nemo.task;

import java.util.ArrayList;
import java.util.Stack;

import nemo.exception.NemoException;

/**
 * Represents a list of Task objects.
 * Supports add, delete, mark, unmark operations.
 *
 * @author Lian Kok Hai
 */

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int taskCount;
    protected Stack<String> undoStates;
    protected Stack<String> redoStates;

    /**
     * Constructs new TaskList.
     */
    public TaskList() {
        taskCount = 0;
        taskList = new ArrayList<>();
        undoStates = new Stack<>();
        redoStates = new Stack<>();
    }

    /**
     * Returns number of tasks in TaskList.
     *
     * @return Integer number of tasks in TaskList.
     */
    public int getCount() {
        return taskCount;
    }

    /**
     * Adds a task into TaskList.
     *
     * @param task New task to be added.
     */
    public void addTask(Task task) {
        undoStates.push(this.encode());
        redoStates.clear();
        taskList.add(task);
        taskCount++;
    }

    /**
     * Deletes a task.
     *
     * @param taskNumber 1-based index of task.
     * @return Deleted Task
     * @throws NemoException Thrown when index given is out of bounds.
     */
    public Task deleteTask(int taskNumber) throws NemoException {
        assert taskNumber > 0 && taskNumber <= taskCount : "index should be positive and within range";
        try {
            undoStates.push(this.encode());
            redoStates.clear();
            Task deletedTask = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            taskCount--;
            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new NemoException("No task with given task number of " + taskNumber);
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber 1-based index of task.
     * @return Marked Task.
     * @throws NemoException Thrown when index given is out of bounds.
     */
    public Task markTask(int taskNumber) throws NemoException {
        assert taskNumber > 0 && taskNumber <= taskCount : "index should be positive and within range";
        try {
            undoStates.push(this.encode());
            redoStates.clear();
            Task task = taskList.get(taskNumber - 1);
            task.markDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new NemoException("No task with given task number of " + taskNumber);
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param taskNumber 1-based index of task.
     * @return Unmarked Task.
     * @throws NemoException Thrown when index given is out of bounds.
     */
    public Task unmarkTask(int taskNumber) throws NemoException {
        assert taskNumber > 0 && taskNumber <= taskCount : "index should be positive and within range";
        try {
            undoStates.push(this.encode());
            redoStates.clear();
            Task task = taskList.get(taskNumber - 1);
            task.unmarkDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new NemoException("No task with given task number of " + taskNumber);
        }
    }

    /**
     * Lists all tasks in TaskList
     *
     * @return String representation of all tasks in list.
     */
    public String listTasks() {
        String result = "";
        result += "Here are the tasks in your list:\n";
        for (int i = 0; i < taskCount; i++) {
            result += String.format("%d. %s \n", i + 1, taskList.get(i));
        }
        return result;
    }

    /**
     * Encodes TaskList into a String representation to save into text file.
     * Uses each Tasks own encode method.
     *
     * @return Encoded String representation of TaskList.
     */
    public String encode() {
        String result = "";
        for (int i = 0; i < taskCount; i++) {
            result += String.format("%s\n", taskList.get(i).encode());
        }
        return result;
    }

    /**
     * Decodes an encoded String representation of TaskList.
     *
     * @param encodedString Encoded String representation of TaskList.
     * @return An ArrayList of Tasks decoded from String.
     * @throws NemoException when an invalid String is encountered.
     */
    public ArrayList<Task> decode(String encodedString) throws NemoException {
        ArrayList<Task> output = new ArrayList<>();
        String[] splitStrings = encodedString.split("\n");
        for (String str : splitStrings) {
            output.add(Task.decode(str));
        }
        return output;
    }

    /**
     * Loads an ArrayList of Tasks.
     * Used to initialise TaskList from saved text file.
     *
     * @param taskList ArrayList of Tasks.
     */
    public void loadTasks(ArrayList<Task> taskList) {
        this.taskList = taskList;
        taskCount = taskList.size();
    }

    /**
     * Finds all Tasks that have names containing the given String.
     *
     * @param strings String array to search task names.
     * @return String representation of all Tasks containing given String
     */
    public String findTasks(String... strings) {
        assert strings.length > 0 : "unable to search for empty string array";
        String result = "";
        result += "Here are the matching tasks in your list:\n";
        int counter = 0;
        for (int i = 0; i < taskCount; i++) {
            for (String str : strings) {
                if (taskList.get(i).getTaskName().contains(str)) {
                    counter++;
                    result += String.format("%d. %s \n", counter, taskList.get(i));
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Undoes the previous change made to TaskList.
     *
     * @return True if successfully undone.
     * @throws NemoException when error encountered during encoding.
     */
    public boolean undo() throws NemoException {
        if (!undoStates.isEmpty()) {
            redoStates.push(this.encode());
            String prevState = undoStates.pop();
            this.loadTasks(this.decode(prevState));
            return true;
        }
        return false;
    }

    /**
     * Redoes previously undone change.
     *
     * @return True if successfully redone.
     * @throws NemoException when error encountered during encoding.
     */
    public boolean redo() throws NemoException {
        if (!redoStates.isEmpty()) {
            undoStates.push(this.encode());
            String redoState = redoStates.pop();
            this.loadTasks(this.decode(redoState));
            return true;
        }
        return false;
    }
}
