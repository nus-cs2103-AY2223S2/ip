package aqua.manager;

import java.util.List;

import aqua.aquatask.AquaTask;


/** A data class to stored filter data from filter operations in {@code TaskManager} */
public class TaskFilterReport {
    /** The filtered list of tasks. */
    public final List<AquaTask> filtered;
    /** The remaining tasks. */
    public final List<AquaTask> remaining;


    public TaskFilterReport(List<AquaTask> filtered, List<AquaTask> remaining) {
        this.filtered = List.copyOf(filtered);
        this.remaining = List.copyOf(remaining);
    }
}
