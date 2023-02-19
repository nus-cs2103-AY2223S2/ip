package tasks;

import java.util.Comparator;

/**
 * Represents a comparator for comparing tasks based on their priority levels.
 * Tasks are compared based on the priority level of their {@link Task#getPriority() priority} field.
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares two tasks based on their priority levels.
     *
     * @param t1 the first task to compare
     * @param t2 the second task to compare
     * @return a negative integer, zero, or a positive integer if the first task has a lower, equal,
     *         or higher priority level than the second task, respectively
     */
    public int compare(Task t1, Task t2) {
        int t1Level = Priority.getPriorityLevel(t1.priority);
        int t2Level = Priority.getPriorityLevel(t2.priority);
        return t1Level - t2Level;
    }
}
