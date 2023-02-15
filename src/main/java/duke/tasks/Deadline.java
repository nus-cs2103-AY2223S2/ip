package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;
import java.time.format.DateTimeParseException;

/**
 * Inherits abstract class Task. Characterised by description and due date.
 *
 * @author jengoc415
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructor for task type: deadline
     *
     * @param instruction full instruction keyed in by user
     * @throws DukeException erroneous entry by user
     * @throws DateTimeParseException incorrect due date format
     */
    public Deadline(String instruction) throws DukeException, DateTimeParseException {
        super(instruction);
        this.modifiedInstr = instruction.substring(9);
        String[] instrSplit = modifiedInstr.split(" /by ");
        if (instrSplit.length == 1) {
            throw new DukeException("You forgot to include a deadline.\nUsage: 'deadline task /by YYYY-MM-DD'\n");
        }
        this.description = instrSplit[0];
        this.dueDate = LocalDate.parse(instrSplit[1]);
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s, %s)",
                super.toString(),
                description,
                this.dueDate.getDayOfWeek().toString().toLowerCase(),
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }
}
