package duke.tasklist;

import duke.dukeexception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public String toString() {
        if (tasks.size() == 0) {
            return "You do not have any tasks yet";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("All Tasks:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append("    " + i + ". " + tasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }

    public Task mark(int taskNumber) throws DukeException {
        try {
            tasks.get(taskNumber - 1).mark();
        } catch (Exception e) {
            throw new DukeException("duke.task.Task does not exist, current number of tasks: " + tasks.size());
        }
        return tasks.get(taskNumber - 1);
    }

    public Task unmark(int taskNumber) throws DukeException {
        try {
            tasks.get(taskNumber - 1).unmark();
        } catch (Exception e) {
            throw new DukeException("duke.task.Task does not exist, current number of tasks: " + tasks.size());
        }
        return tasks.get(taskNumber - 1);
    }

    public Task remove(int taskNumber) throws DukeException {
        try {
            return tasks.remove(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("duke.task.Task does not exist, current number of tasks: " + tasks.size());
        }
    }
    public String find(String searchLine) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(searchLine)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            return "No related tasks found";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Found related tasks:\n");
        for (int i = 1; i <= foundTasks.size(); i++) {
            sb.append("    " + i + ". " + foundTasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }

    public String getWriteString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toStoreFormatString() + System.lineSeparator());
        }
        return sb.toString();
    }
}
