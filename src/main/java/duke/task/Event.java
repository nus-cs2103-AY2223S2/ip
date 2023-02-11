package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains the Event task object with variables from and to
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HHmm");


    /**
     * Constructor
     *
     * @param value = name of the ask
     * @param from = datetime of the task beginning
     * @param to = datetime of the task ending
     * @param mark = the status of the task, whether it is completed
     */
    public Event(String value, LocalDateTime from, LocalDateTime to, boolean mark ) {
        super(value,mark);
        this.from = from;
        this.to = to;
    }

    /**
     *
     * @return = returns a string that represents the event object
     *         that is stored in a file
     */
    public String toFile() {
        return "event;" + super.isMark() + ";" + super.getValue() + ";" + this.from + ";" + this.to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from.format(formatter) +
                " to: " + to.format(formatter) + ")";
    }
}
