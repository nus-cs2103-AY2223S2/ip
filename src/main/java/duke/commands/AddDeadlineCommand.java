package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
/**
 * This is the AddDeadlineCommand class to represent add deadline commands passed to Duke.
 * Encapsulates the information needed to add a Deadline object to the TaskList.
 */
public class AddDeadlineCommand extends AddCommand {
    /**
     * Creates a new AddDeadlineCommand.
     * @param tokens {@inheritDoc}
     * @throws DukeException when exceptions are encountered in creating the Deadline.
     */
    public AddDeadlineCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        LocalDate by;
        int byId = tokens.indexOf("/by");
        if (byId < 0) {
            throw new DukeException("Invalid input received! \ntasks.Deadline commands are in the form of: deadline "
                    + "name /by date \n(remember to include '/by')");
        }
        String name = String.join(" ", tokens.subList(1, byId));

        try {
            by = LocalDate.parse(String.join(" ", tokens.subList(byId + 1, tokens.size())));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! \n date must be in the form of YYYY-MM-DD");
        }

        Deadline newDeadline = new Deadline(name, by);
        setTaskToAdd(newDeadline);
    }
}
