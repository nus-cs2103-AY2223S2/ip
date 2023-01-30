package duke.util;

import java.util.ArrayList;
import java.util.List;


public class TaskList {
    List<Task> listTask;

    public TaskList() {
        this.listTask = new ArrayList<Task>();
    }

    public TaskList markDone(int index) {
        Task currentTask = this.listTask.get(index);
        this.listTask.set(index, currentTask.markDone());
        return this;
    }

    public TaskList unMark(int index) {
        Task currentTask = this.listTask.get(index);
        this.listTask.set(index, currentTask.unMark());
        return this;
    }

    public TaskList addTask(Task task) {
        this.listTask.add(task);
        return this;
    }

    public String getTask(int index) {
        return this.listTask.get(index).toString();
    }

    public int getSize() {
        return this.listTask.size();
    }

    public TaskList removeTask(int index) {
        this.listTask.remove(index);
        return this;
    }

    @Override
    public String toString() {
        if (this.listTask.isEmpty()) {
            return "NOTHING ADDED TO LIST";
        } else {
            String toPrintOut = "";
            for (int i = 0; i < this.listTask.size(); i++) {
                toPrintOut += (i + 1) + ". " + this.listTask.get(i).toString() + '\n';
            }
            return toPrintOut;
        }
    }
}