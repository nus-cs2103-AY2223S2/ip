package duke;

import java.util.Comparator;

import duke.task.Task;
import duke.task.TaskWithDate;

/**
 * TaskComparator to sort task list.
 */
public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getType().equals("T")) {
            if (t2.getType().equals("T")) {
                return t1.getDesc().compareTo(t2.getDesc());
            } else {
                return -1;
            }
        } else if (t2.getType().equals("T")) {
            assert !t1.getType().equals("T") : "T1 should not be ToDo type!";
            return 1;
        } else {
            assert !t1.getType().equals("T") : "T1 should not be ToDo type!";
            assert !t2.getType().equals("T") : "T2 should not be ToDo type!";
            TaskWithDate task1 = (TaskWithDate) t1;
            TaskWithDate task2 = (TaskWithDate) t2;
            boolean isBefore = task1.getDateForComparison().isBefore(task2.getDateForComparison());
            if (isBefore) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
