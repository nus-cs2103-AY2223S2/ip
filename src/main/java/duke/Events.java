package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    public Events(String name, String startDate, String endDate) {
        super(name, "E");
        this.startDate = makeDateTime(startDate);
        this.endDate = makeDateTime(endDate);
    }

    public Events(String name, String startDate, String endDate, boolean isDone) {
        super(name, "E", isDone);
        this.startDate = makeDateTime(startDate);
        this.endDate = makeDateTime(endDate);
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
