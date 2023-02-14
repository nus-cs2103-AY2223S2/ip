package duke.tasks.taskcomparator;

import java.util.Comparator;

import duke.tasks.Task;

/**
 * DescriptionComparator compares tasks lexicographically based on their description.
 * This gives tasks in an ascending order.
 */
public class DescriptionComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getDescription().compareTo(t2.getDescription());
    }
}
