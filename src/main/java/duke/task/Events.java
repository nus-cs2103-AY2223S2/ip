package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDateTime startTime;

    protected LocalDateTime endTime;

<<<<<<< Updated upstream
=======
    /**
     * A constructor of events object
     *
     * @param description the task to be stored
     * @param startTime   the time the task starts
     * @param endTime     the time the task ends
     */
>>>>>>> Stashed changes
    public Events(String description, String startTime, String endTime) {
        super(description);
        DateTimeFormatter formatterStart = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm ");
        this.startTime = LocalDateTime.parse(startTime, formatterStart);
        DateTimeFormatter formatterEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.endTime = LocalDateTime.parse(endTime, formatterEnd);
    }
<<<<<<< Updated upstream
=======

    /**
     * A to string method of format [E][] (from: MMM d yyyy HHmm to: MMM d yyyy HHmm)
     *
     * @return the string of above specifications
     */
>>>>>>> Stashed changes
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) +
                " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}