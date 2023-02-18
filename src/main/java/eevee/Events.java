package eevee;

public class Events extends Task {

    public Events(String name, String startDate, String endDate) {
        super(name, "E", makeDateTime(startDate), makeDateTime(endDate));
    }

    public Events(String name, String startDate, String endDate, boolean isDone) {
        super(name, "E", makeDateTime(startDate), makeDateTime(endDate), isDone);
    }

    /**
     * Adds on the start and end date to the <code>description()</code> method in
     * <code>Task</code>.
     * @return a <code>String</code> in the format to be output to the user
     */
    @Override
    public String getDescription() {
        return String.format("%s (from: %s to: %s)", super.getDescription(),
                formatDateTime(this.startDate), formatDateTime(this.endDate));
    }

    /**
     * Adds on the start and end date to the <code>formatDescription()</code>
     * method in <code>Task</code> class.
     * @return a <code>String</code> to be added in format of the task list
     * representing this <code>Events</code> object.
     */
    @Override
    public String formatDescription() {
        return super.formatDescription()
                + String.format(" | %s | %s", formatDateTimeForTaskList(startDate),
                formatDateTimeForTaskList(endDate));
    }
}
