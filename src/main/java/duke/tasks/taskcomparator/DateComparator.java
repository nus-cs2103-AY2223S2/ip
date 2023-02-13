package duke.tasks.taskcomparator;

import java.util.Comparator;

import duke.tasks.Task;

public class DateComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.hasDate() && t2.hasDate()) {
            return t1.getDate().compareTo(t2.getDate());
        } else {
            return t1.getDescription().compareTo(t2.getDescription());
        }
    }
}
