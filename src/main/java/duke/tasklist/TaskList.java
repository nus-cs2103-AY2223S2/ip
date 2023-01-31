package duke.tasklist;

import java.util.ArrayList;

import duke.dukeexception.DukeException;
import duke.task.Task;

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
        sb.append("1. " + tasks.get(0) + "\n");
        for (int i = 2; i <= tasks.size(); i++) {
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

    public String getWriteString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toStoreFormatString() + System.lineSeparator());
        }
        return sb.toString();
    }
}
