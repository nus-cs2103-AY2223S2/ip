package duke.tasklist;


import duke.exception.TaskNumberNotFoundException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int length() {
        return this.taskList.size();
    }

    public Task getTask(int taskNumber) throws TaskNumberNotFoundException {
        try {
            return this.taskList.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberNotFoundException();
        }
    }

    public void deleteTask(int taskNumber) throws TaskNumberNotFoundException {
        try {
            this.taskList.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberNotFoundException();
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
