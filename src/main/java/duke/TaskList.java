package duke;

import java.util.List;

public class TaskList {

    private final List<Task> tasks;
    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    // List out all tasks and their rank.
    protected void list() {
        if (tasks.size() == 0) {
            System.out.println("No tasks left :)");
            return;
        }
        int rank = 1;
        for (Task t : tasks) {
            System.out.println(rank + "." + t.fullMessage());
            rank++;
        }
    }

    /**
     * Lists out all tasks with matching keyword.
     *
     * @param keyword Keyword to match.
     */
    protected void find(String keyword) {
        int rank = 1;
        System.out.println("Looking for matching tasks in your list...");
        for (Task t : tasks) {
            if (t.toString().contains(keyword)) {
                System.out.println(rank + "." + t.fullMessage());
                rank++;
            }
        }
        if (rank == 1) {
            System.out.println("Cannot find any matching task!");
        }
    }

    // Mark task at index to be done
    protected Task markDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index");
        }

        Task curr = tasks.get(index);
        curr.markAsDone();
        return curr;

    }

    // Mark task at index to be undone
    protected Task markUndone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index.");
        }

        Task curr = tasks.get(index);
        curr.markAsUndone();
        return curr;
    }

    // Delete task at index.
    protected Task delete(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index.");
        }
        return tasks.remove(index);
    }

    // Create and add task given message and code. 0 - toDos, 1 - Deadlines, 2 - duke.Event
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

    protected int getSize() {
        return tasks.size();
    }

    protected static Task getInstance(String code, boolean status, String[] content) throws DukeException {
        if (code.equals("T")) {
            return new ToDo(status, content);
        } else if (code.equals("D")) {
            return new Deadline(status, content);
        } else if (code.equals("E")) {
            return new Event(status, content);
        } else {
            throw new DukeException("Unsupported code");
        }
    }


}
