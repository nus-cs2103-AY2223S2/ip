package seedu.duke;

import seedu.duke.tasks.*;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public TaskList addTask(Task newTask) {
        ArrayList<Task> updatedTasks = this.tasks;
        updatedTasks.add(newTask);
        return new TaskList(updatedTasks);
    }

    public TaskList deleteTask(int index) {
        ArrayList<Task> updatedTasks = this.tasks;
        updatedTasks.remove(index);
        return new TaskList(updatedTasks);
    }

    public TaskList markTask(int index) throws DukeException {
        ArrayList<Task> updatedTasks = this.tasks;
        Task unmarkedTask = updatedTasks.get(index);
        Task markedTask = unmarkedTask.markTask();
        updatedTasks.set(index, markedTask);
        return new TaskList(updatedTasks);
    }

    public TaskList unmarkTask(int index) throws DukeException {
        ArrayList<Task> updatedTasks = this.tasks;
        Task markedTask = updatedTasks.get(index);
        Task unmarkedTask = markedTask.unmarkTask();
        updatedTasks.set(index, unmarkedTask);
        return new TaskList(updatedTasks);
    }

    public String formatTask(int index) {
        return get(index).formatTask();
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public boolean equals (Object o) {
        if (o instanceof TaskList) {
            TaskList taskList = (TaskList) o;
            if(taskList.tasks.equals(this.tasks)) {
                return true;
            }
            return true;
        }
        return  false;
    }
}
