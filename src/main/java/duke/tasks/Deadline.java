package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String description;
    private LocalDate dueDate;

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
