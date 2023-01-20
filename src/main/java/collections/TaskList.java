package collections;

import models.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public void mark(int i) { super.get(i-1).mark(); }

    public void unMark(int i) { super.get(i-1).unMark(); }

    @Override
    public Task get(int i) { return super.get(i-1); }

    @Override
    public Task remove(int i) {
        Task removedTask = super.remove(i-1);
        return removedTask;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this) {
            if (i == size()) {
                sb.append(i+".").append(task).append("\t");
            } else {
                sb.append(i + ".").append(task).append("\n\t");
                i++;
            }
        }
        return sb.toString();
    }
}
