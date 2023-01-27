package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public String listTasks() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += "     " + (i + 1) + ". " + tasks.get(i).toString();
            if (i < tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    public void markTask(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number invalid");
        }
        tasks.get(taskNum).mark();
    }

    public void unmarkTask(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number invalid");
        }
        tasks.get(taskNum).unmark();
    }

    public Task deleteTask(int taskNum) throws DukeException {
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number invalid");
        }
        Task removedTask = tasks.get(taskNum);
        tasks.remove(taskNum);
        return removedTask;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getLatestTask() {
        return tasks.get(tasks.size() - 1);
    }

    public Task getTask(int taskNum) { // get task according to the index of the array
        return tasks.get(taskNum);
    }
}