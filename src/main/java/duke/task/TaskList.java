package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Represents a list of Task objects.
 * Supports add, delete, mark, unmark operations.
 *
 * @author Lian Kok Hai
 */

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int taskCount;

    public TaskList() {
        this.taskCount = 0;
        this.taskList = new ArrayList<>();
    }

    public int getCount() {
        return taskCount;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        taskCount++;
    }

    public Task deleteTask(int taskNumber) throws DukeException {
        try {
            Task deletedTask = this.taskList.get(taskNumber - 1);
            this.taskList.remove(taskNumber - 1);
            taskCount--;
            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    public String markTask(int taskNumber) throws DukeException {
        try {
            Task task = this.taskList.get(taskNumber - 1);
            task.markDone();
            return "Nice! I've marked this task as done: \n" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    public String unmarkTask(int taskNumber) throws DukeException {
        try {
            Task task = this.taskList.get(taskNumber - 1);
            task.unmarkDone();
            return "OK, I've marked this task as not done yet: \n" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No task with given task number of " + taskNumber);
        }
    }

    public String listTasks() {
        String result = "";
        result += "Here are the tasks in your list:\n";
        for (int i = 0; i < this.taskCount; i++) {
            result += String.format("%d. %s \n", i + 1, this.taskList.get(i));
        }
        return result;
    }

    public String encode() {
        String result = "";
        for (int i = 0; i < this.taskCount; i++) {
            result += String.format("%s\n", this.taskList.get(i).encode());
        }
        return result;
    }

    public void loadTasks(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
    }
}
