package duke;

import java.util.Comparator;

public class TaskDateComparator implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        if (t1.containsDate() != t2.containsDate()) {
            return t1.containsDate() - t2.containsDate();
        } else {
            return t1.getDate().compareTo(t2.getDate());
        }
    }
}
