package duke;

import java.util.Comparator;

/**
 * A Comparator for task, to be used for Sort command.
 */
public class TaskDateComparator implements Comparator<Task> {

    /**
     * Compares two tasks.
     * @param t1 the first object to be compared.
     * @param t2 the second object to be compared.
     * @return Positive integer if t1 is greater,
     *     negative integer if t2 is greater,
     *     0 if t1 is equals to t2.
     */
    public int compare(Task t1, Task t2) {
        if (t1.containsDate() != t2.containsDate()) {
            return t1.containsDate() - t2.containsDate();
        } else {
            return t1.getDate().compareTo(t2.getDate());
        }
    }
}
