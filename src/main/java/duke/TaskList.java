package duke;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents an iterable list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public boolean add(Task t) {
        return taskList.add(t);
    }

    public Task remove(int i) {
        return taskList.remove(i);
    }

    public int size() {
        return taskList.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    public String toStorageString() {
        String output = "";
        for (Task t : this.taskList) {
            String taskDesc = t.getDescription();
            Integer isDone = (t.isDone()) ? 1 : 0;

            if (t instanceof Deadline) {
                Deadline task = (Deadline) t;
                output += "D";
                output += "/" + isDone;
                output += "/" + taskDesc;
                output += "/" + task.getByInStorageFormat();
            } else if (t instanceof Event) {
                Event task = (Event) t;
                output += "E";
                output += "/" + isDone;
                output += "/" + taskDesc;
                output += "/" + task.getFromInStorageFormat();
                output += "/" + task.getToInStorageFormat();
            } else if (t instanceof ToDo) {
                output += "T";
                output += "/" + isDone;
                output += "/" + taskDesc;
            }
            output += System.lineSeparator();
        }

        return output;
    }
}
