package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.Parser;
import duke.exceptions.DeadlineByNotSpecified;
import duke.exceptions.TaskNameNotSpecified;

public class Deadline extends Task {
    private String dueDate;
    private Optional<LocalDate> chornoDueDate;

    // Factory method
    public static Deadline create(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String[] parseInfo = Parser.parseDeadlineCmd(commandInput);
        return new Deadline(parseInfo[0], parseInfo[1], false);
    }
    
    public Deadline(String taskName, String dueDate, boolean isDone) {
        super(taskName, "D", isDone);
        this.dueDate = dueDate;
        this.chornoDueDate = Parser.parseDate(dueDate); 
    }

    @Override
    public String stringFields() {
        String dateString = this.chornoDueDate.isEmpty() ? dueDate : 
        chornoDueDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        
        return " (by: " + dateString + ")"; 
    }
}
