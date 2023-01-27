package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The duke.task.TaskList class represent a list of all the tasks added by the user.
 *
 * @author Chia Jeremy
 */

public class TaskList {

    private final ArrayList<Task> taskList = new ArrayList<>(100);

    public TaskList(List<String> tasks) {
        for (String t : tasks) {
            String[] data = t.split(" \\| ");
            String type = data[0], mark = data[1], descr = data[2];
            Task task;
            if (type.equals("T")) {
                task = new Todo(descr);
            } else if (type.equals("D")) {
                LocalDateTime dateTime = LocalDateTime.parse(data[3].trim());
                task = new Deadline(descr, dateTime);
            } else {
                LocalDateTime startDt = LocalDateTime.parse(data[3].trim());
                LocalDateTime endDt = LocalDateTime.parse(data[4].trim());
                task = new Event(descr, startDt, endDt);
            }
            if (mark.equals("X")) {
                task.markDone();
            } else {
                task.unmarkDone();
            }
            add(task);
        }
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void delete(int index) {
        this.taskList.remove(index);
    }

    public void mark(int index) {
        getTask(index).markDone();
    }

    public void unmark(int index) {
        getTask(index).unmarkDone();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int getSize() {
        return this.taskList.size();
    }
}
