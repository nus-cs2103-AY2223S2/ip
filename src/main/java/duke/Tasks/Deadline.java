package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class representing a Deadline taks.
 */
public class Deadline extends Task {
    private static final String FORMAT = "deadline {task name} /by {dd/mm/yyyy HHmm}";
    private LocalDateTime deadline;
    /** Format of DateTime accepted to create a Deadline object. */
    private static final DateTimeFormatter IN_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd/MM/yyyy HHmm");
    /** User friendly format of DateTime which is displayed to the user. */
    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd LLL, h:mma");

    /**
     * The constructor that initialises a Deadline task with the given description and date.
     * 
     * @param desc Description of a Deadline task to be created.
     * @param deadline Deadline of the Deadline task to be created.
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
     * User friendly guide to help users when InvalidCommandException is thrown.
     * 
     * @return String represeting the fomat of the Deadline
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
