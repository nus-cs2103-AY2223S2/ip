package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents Deadline Task
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu");
    private LocalDate deadline;

    /**
     * Execute adding a Deadline command
     * @param taskName
     * @param deadline
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        String[] fromDate = deadline.split(" ", 2);
        LocalDate ld = LocalDate.parse(fromDate[1].replace(" ", ""));
        this.deadline = ld;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + deadline.format(FORMAT) + ")";
    }
}
