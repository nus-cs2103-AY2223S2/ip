package duke; 

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String task;
    private LocalDateTime deadline; 
    private DateTimeFormatter acceptingFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy HHmm");

    public Deadline(String task, String deadline) {
        super(task);
        try {
            LocalDateTime temp = LocalDateTime.parse(deadline, acceptingFormat);
            this.deadline = LocalDateTime.parse(temp.format(displayFormat), displayFormat);
            this.task = task;
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(deadline, deadline, 0);
        }
    }

    @Override
    public String getTask() {
        return this.task;
    }

    public String getDeadline() {
        return this.deadline.format(displayFormat);
    }
    
    @Override 
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline.format(displayFormat) + "Hrs" + ")";
    }
}
