package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.Parser;
import duke.exceptions.EventFromToNotSpecified;
import duke.exceptions.TaskNameNotSpecified;

public class Event extends Task {
    private String fromDate;
    private String toDate;
    private Optional<LocalDate> chronoFromDate;
    private Optional<LocalDate> chronoToDate;

    public static Event create(String commandInput) throws TaskNameNotSpecified, EventFromToNotSpecified {
        String[] parseInfo = Parser.parseEventCmd(commandInput);
        return new Event(parseInfo[0], parseInfo[1], parseInfo[2], false);
    }

    public Event(String taskName, String fromDate, String toDate, boolean isDone) {
        super(taskName, "E", isDone);
  
        this.fromDate = fromDate;
        this.chronoFromDate = Parser.parseDate(fromDate);

        this.toDate = toDate;
        this.chronoToDate = Parser.parseDate(toDate);

        this.isDone = isDone;
    }

    
    @Override
    public String stringFields() {
        String fromDateString = this.chronoFromDate.isEmpty() ? this.fromDate : this.chronoFromDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toDateString = this.chronoToDate.isEmpty() ? this.toDate : this.chronoToDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return " (from: " + fromDateString + " to: " + toDateString + ")";
    }
}