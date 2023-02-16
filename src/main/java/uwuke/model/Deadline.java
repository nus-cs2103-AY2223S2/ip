package uwuke.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Optional;

public class Deadline extends Task {

    private String deadlineString;
    private Optional<LocalDateTime> deadlineTime = Optional.empty();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String task, String deadline) {
        super(task);
        this.deadlineString = deadline;
        try {
            this.deadlineTime = Optional.of(LocalDateTime.parse(deadline, formatter));
        } catch (DateTimeParseException e) {
            // // Comment out if this warning gets annoying
            // Printer.printError("Could not parse date. Ignore this if you weren't trying to input a date!");
        }
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", 
                            "[D]", 
                            super.toString(),
                            deadlineTime.map(dl -> dl.format(formatter)).orElse(deadlineString));
    }

    /**
     * Checks for whether this task is going to be due in less than 24 hours.
     * If the deadline is not parsed as a LocalDateTime in this object's construction, 
     * then this will always return false.
     * @return if this task is due in 24 hours
     */
    public boolean isDueSoon() {
        return deadlineTime.map(this::isDueSoonHelper).orElse(false);
    }

    private boolean isDueSoonHelper(LocalDateTime deadline) {
        LocalDateTime now = LocalDateTime.now();
        boolean isAfterNow = deadline.isAfter(now);
        boolean isMoreThanOneDayFromNow = now.plusDays(1).isAfter(deadline);
        return isAfterNow && isMoreThanOneDayFromNow && !isDone();
    }
}