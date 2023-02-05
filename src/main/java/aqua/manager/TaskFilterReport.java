package aqua.manager;

import java.util.List;

import aqua.aquatask.AquaTask;


/** A data class to stored filter data from filter operations in {@code TaskManager} */
public class TaskFilterReport {
    /** The filtered list of tasks. */
    public final List<AquaTask> filtered;
    /** Tasks that cannot be filtered correctly. */
    public final List<AquaTask> unknown;


    public TaskFilterReport(List<AquaTask> filtered, List<AquaTask> unknown) {
        this.filtered = List.copyOf(filtered);
        this.unknown = List.copyOf(unknown);
    }
}
