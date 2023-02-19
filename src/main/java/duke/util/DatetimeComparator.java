package duke.util;

import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.util.Pair;

/**
 * A class used to compare dates to determine the order of dates in a PriorityQueue.
 * Tasks with a sooner date are placed first in the queue.
 *
 */
public class DatetimeComparator implements Comparator<Pair<LocalDateTime, Task>> {
    @Override
    public int compare(Pair<LocalDateTime, Task> a, Pair<LocalDateTime, Task> b) {
        return a.getKey().compareTo(b.getKey());
    }
}
