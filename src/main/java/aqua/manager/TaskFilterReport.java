package aqua.manager;

import java.util.List;

import aqua.usertask.UserTask;


/** A data class to stored filter data from filter operations in {@code TaskManager} */
public class TaskFilterReport {
    /** The filtered list of tasks. */
    public final List<UserTask> filtered;
    /** Tasks that cannot be filtered correctly. */
    public final List<UserTask> unknown;


    /**
     * Constructs a {@code TaskFilterReport} from the given parameters.
     *
     * @param filtered - list of filtered tasks
     * @param unknow - list of tasks that cannot be filtered.
     */
    public TaskFilterReport(List<UserTask> filtered, List<UserTask> unknown) {
        this.filtered = List.copyOf(filtered);
        this.unknown = List.copyOf(unknown);
    }
}
