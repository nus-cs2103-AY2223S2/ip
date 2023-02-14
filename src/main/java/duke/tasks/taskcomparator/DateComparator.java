package duke.tasks.taskcomparator;

import java.util.Comparator;

import duke.tasks.Task;

/**
 * DateComparator compares the START DATE of tasks if the task has Date associated to it.
 * Else, task string is used to compare. This lets sorted tasks to be grouped by task type.
 * This gives tasks in an ascending order by date.
 */
public class DateComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.hasDate() && t2.hasDate()) {
            return t1.getDate().compareTo(t2.getDate());
        } else {
            return t1.toString().compareTo(t2.toString());
        }
    }
}
