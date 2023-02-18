package eevee.task;

/**
 * Represents a Task that has a due date.
 */
public class Deadlines extends Task {

    public Deadlines(String name, String dueDate) {
        super(name, "D", makeDateTime(dueDate), null);
    }

    public Deadlines(String name, String dueDate, boolean isDone) {
        super(name, "D", makeDateTime(dueDate), null, isDone);
    }

    /**
     * Adds on the due date to the <code>description()</code> method in
     * <code>Task</code>.
     * @return a <code>String</code> in the format to be output to the user
     */
    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", super.getDescription(),
                formatDateTime(this.startDate));
    }

    /**
     * Adds on the due date to the <code>formatDescription()</code> method in
     * <code>Task</code> class.
     * @return a <code>String</code> to be added into the task list representing
     * this <code>Deadline</code> object.
     */
    @Override
    public String formatDescription() {
        return super.formatDescription()
                + String.format(" | %s", formatDateTimeForTaskList(this.startDate));
    }
}
