package duke.tasklist;


import duke.exception.TaskNumberNotFoundException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int length() {
        return this.tasks.size();
    }

    public Task getTask(int taskNumber) throws TaskNumberNotFoundException {
        try {
            return this.tasks.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberNotFoundException();
        }
    }

    public void deleteTask(int taskNumber) throws TaskNumberNotFoundException {
        try {
            this.tasks.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberNotFoundException();
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
