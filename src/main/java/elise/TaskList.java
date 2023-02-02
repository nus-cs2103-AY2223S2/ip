package elise;

import java.util.List;

/**
 * TaskList manages operations regarding Task.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks Initial tasks.
     */
    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists out all tasks and their status.
     */
    protected String list() {
        if (tasks.size() == 0) {
            return "No tasks left :)";
        }
        int rank = 1;
        String out = "";
        for (Task t : tasks) {
            out += rank + "." + t.fullMessage() + "\n";
            rank++;
        }
        return out.trim();
    }

    /**
     * Lists out all tasks with matching keyword.
     *
     * @param keyword Keyword to match.
     */
    protected String find(String keyword) {
        int rank = 1;
        // Matches exact keyword. Do not match part of word.
        String regex = ".*\\b" + keyword + "\\b.*";
        String out = "";
        System.out.println("Looking for matching tasks in your list...");
        for (Task t : tasks) {
            if (t.toString().matches(regex)) {
                out += rank + "." + t.fullMessage() + "\n";
                rank++;
            }
        }
        if (rank > 1) {
            return out.trim();
        } else {
            return "Cannot find any matching task!";
        }
    }

    /**
     * Marks task at index as completed.
     *
     * @param index Index of the task.
     * @return The task at the index.
     * @throws EliseException Invalid index.
     */
    protected Task markDone(int index) throws EliseException {
        if (index < 0 || index >= tasks.size()) {
            throw new EliseException("OOPS!!! Invalid index");
        }

        Task curr = tasks.get(index);
        curr.markAsDone();
        return curr;

    }

    /**
     * Marks task at index as not completed.
     *
     * @param index Index of the task.
     * @return The task at the index.
     * @throws EliseException Invalid index.
     */
    protected Task markUndone(int index) throws EliseException {
        if (index < 0 || index >= tasks.size()) {
            throw new EliseException("OOPS!!! Invalid index.");
        }

        Task curr = tasks.get(index);
        curr.markAsUndone();
        return curr;
    }

    /**
     * Deletes task at index.
     *
     * @param index Index of the task.
     * @return The task at the index.
     * @throws EliseException Invalid index.
     */
    protected Task delete(int index) throws EliseException {
        if (index < 0 || index >= tasks.size()) {
            throw new EliseException("OOPS!!! Invalid index.");
        }
        return tasks.remove(index);
    }

    /**
     * Creates task specified by the code
     *
     * @param code Code of the task
     * @return The task created.
     */
    protected Task addTask(int code, String[] message) {
        Task t;
        if (code == 0) {
            t = new ToDo(false, message);
        } else if (code == 1) {
            t = new Deadline(false, message);
        } else if (code == 2) {
            t = new Event(false, message);
        } else {
            // Not reachable
            return null;
        }
        tasks.add(t);
        return t;
    }

    /**
     * Returns number of tasks.
     *
     * @return Number of tasks.
     */
    protected int getSize() {
        return tasks.size();
    }

    /**
     * Creates tasks specified by parameters.
     *
     * @param code Code of the task.
     * @param status Completed or not.
     * @param content Content of the task.
     * @return Task created.
     * @throws EliseException Unsupported code.
     */
    protected static Task getInstance(String code, boolean status, String[] content) throws EliseException {
        if (code.equals("T")) {
            return new ToDo(status, content);
        } else if (code.equals("D")) {
            return new Deadline(status, content);
        } else if (code.equals("E")) {
            return new Event(status, content);
        } else {
            throw new EliseException("Unsupported code");
        }
    }
}
