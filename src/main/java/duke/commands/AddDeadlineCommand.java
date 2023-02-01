package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(ArrayList<String> tokens) throws DukeException, DateTimeParseException {
        super(tokens);
        int byId = tokens.indexOf("/by");
        if (byId < 0){
            throw new DukeException("Invalid input received! \ntasks.Deadline commands are in the form of: deadline name /by bytime \n(remember to include '/by')");
        }
        String name = String.join(" ", tokens.subList(1, byId));
        LocalDate by = LocalDate.parse(String.join(" ", tokens.subList(byId+1, tokens.size())));
        Deadline newDeadline = new Deadline(name, by);
        super.setTaskToAdd(newDeadline);
    }
}
