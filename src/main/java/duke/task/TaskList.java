package duke.task;

import duke.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task cur) {
        tasks.add(cur);
    }

    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    public void unmarkTask(int index) throws DukeException {
        try {
            Task cur = tasks.get(index - 1);
            cur.markUndone();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    public void markTask(int index) throws DukeException {
        try {
            Task cur = tasks.get(index - 1);
            cur.markDone();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("That task does not exist!");
        }
    }

    public int getNumTasks() {
        return tasks.size();
    }
}