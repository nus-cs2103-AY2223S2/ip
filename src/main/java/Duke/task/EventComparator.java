package Duke.task;

import java.util.Comparator;

/**
 * A Comparator for sorting Events by start time.
 */
public class EventComparator implements Comparator<Event> {
    @Override
  public int compare(Event event1, Event event2) {
        return event1.getStartTime().compareTo(event2.getStartTime());
    }
}
