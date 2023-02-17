package Duke.task;

import java.util.Comparator;

/**
 * A Comparator for sorting Events by start time.
 */
public class DeadlineComparator implements Comparator<Deadline> {
    @Override
  public int compare(Deadline deadline1, Deadline deadline2) {
        return deadline1.getStartTime().compareTo(deadline2.getStartTime());
    }
}