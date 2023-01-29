package iris;

import java.time.LocalDateTime;
import java.util.ArrayList;

import iris.task.Deadline;
import iris.task.Event;
import iris.task.Task;

/**
 * An ArrayList of tasks with additional functionality
 */
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    /**
     * counts the number of pending tasks
     * @return the number of pending tasks
     */
    public int countUnmarkedTasks() {
        int count = 0;
        for (Task task : this) {
            count += task.isDone() ? 0 : 1;
        }
        return count;
    }
    /**
     * filters task list to return tasks with given keyword in description
     * @param keyword the word to filter the tasks
     * @return filtered tasks
     */
    public TaskList findTask(String keyword) {
        TaskList filtered = new TaskList();
        for (Task task : this) {
            if (task.getName().contains(keyword)) {
                filtered.add(task);
            }
        }
        return filtered;
    }

    /**
     * filters task list to return list of deadlines and events in a certain period of time
     * @param startDate start of period
     * @param endDate end of period
     * @return tasks that fulfill the criteria
     */
    public TaskList dateFilter(LocalDateTime startDate, LocalDateTime endDate) {
        TaskList filtered = new TaskList();
        for (Task task : this) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                LocalDateTime t = d.getDeadline();
                if (t.isBefore(endDate)
                        && t.isAfter(startDate)) {
                    filtered.add(d);
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                LocalDateTime f = e.getFrom();
                LocalDateTime t = e.getTo();
                if (t.isBefore(endDate)
                        && f.isAfter(startDate)) {
                    filtered.add(e);
                }
            }
        }
        return filtered;
    }

    /**
     * returns description of task list in a format easy to store and parse
     * @return description of task list
     */
    public String storageFormat() {
        StringBuilder str = new StringBuilder();
        for (Task task : this) {
            str.append(task.storageFormat());
        }
        return str.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            str = String.join("", str, String.valueOf(i + 1), ". ", this.get(i).toString(), "\n");
        }
        return str;
    }
}
