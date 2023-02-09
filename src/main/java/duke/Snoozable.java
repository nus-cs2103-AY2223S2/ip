package duke;

/**
 * Snoozable interface to help push back tasks by a specified amount
 */
public interface Snoozable {
    public String snoozeDeadline(int days, int hours, int minutes);
    public String snoozeDeadline();
}
