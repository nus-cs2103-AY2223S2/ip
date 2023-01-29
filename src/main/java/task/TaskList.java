package task;

import exception.TaskListIndexException;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {

    }

    public Task get(int i) throws TaskListIndexException {
        try {
            return tasks.get(i);
        } catch (IndexOutOfBoundsException ex) {
            String message = "There's no task #" + i + "! ";
            if (tasks.size() == 1) {
                message += "There is currently 1 task...";
            } else {
                message += "There are currently " + tasks.size() + " tasks...";
            }
            throw new TaskListIndexException(message);
        }
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int i) throws TaskListIndexException {
        try {
            return tasks.remove(i);
        } catch (IndexOutOfBoundsException ex) {
            String message = "There's no task #" + i + "! ";
            if (tasks.size() == 1) {
                message += "There is currently 1 task...";
            } else {
                message += "There are currently " + tasks.size() + " tasks...";
            }
            throw new TaskListIndexException(message);
        }
    }

    public void clear() {
        tasks.clear();
    }

    public int size() {
        return tasks.size();
    }
}
