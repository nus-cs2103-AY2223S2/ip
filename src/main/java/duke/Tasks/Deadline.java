package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class representing a Deadline task.
 */
public class Deadline extends Task {
    private static final String FORMAT = "deadline {task name} /by {dd/mm/yyyy HHmm}";
    private LocalDateTime deadline;
    /** Format of DateTime accepted to create a Deadline task object */
    private static final DateTimeFormatter IN_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd/MM/yyyy HHmm");
    /** User friendly format of DateTime which is displayed to the user */
    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd LLL, h:mma");

    /**
     * The constructor that initalises a Deadline task with the given description and date.
     * 
     * @param desc Description of the deadline task.
     * @param deadline Deadline of the deadline task.
     */
    public Deadline(String desc, String deadline) {
        super(desc);
        setDeadline(deadline);
    }

    private void setDeadline(String deadline) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(deadline, IN_FORMAT);
            this.deadline = dateTime;
        } catch (DateTimeParseException d) {
            System.out.println("Invalid date/time format for Deadline.");
        }
    }
    
    private String deadline() {
        return " (by: " + this.deadline.format(OUT_FORMAT) + ")";
    }

    /**
     * User friendly guide to help users in case of InvalidCommandExcpetion.
     * @retur String representing the format of Deadline task.n
     */
    public static String showFormat() {
        return "Create a `deadline` with: " + FORMAT;
    }

    /**
     * User friendly interpretation of Deadline task object.
     * Displays Task type, description and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + this.deadline();
    }
}
